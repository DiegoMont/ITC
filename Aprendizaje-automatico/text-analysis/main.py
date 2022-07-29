from collections import Counter
import pandas as pd
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.pipeline import Pipeline
from sklearn.naive_bayes import MultinomialNB
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
import spacy
import time


ENGLISH_DATASET_FILENAME = "English.txt"
GOLD_DATASET_FILENAME = "Gold-Ingles.csv"
NUM_INSTANCES = 1000
ID_COLUMN = "ID"
AGE_COLUMN = "Age"
GENDER_COLUMN = "GENDER"
TEXT_COLUMN = "Text"
DOC_COLUMN = "Spacy Doc"
RELEVANT_WORDS_COLUMN = "Relevant words"
CLASS1_VALUE = "female"
RELEVANT_POS = ["ADJ", "NOUN", "PROPN", "VERB"]
SIMPLIFIED_TEXT_COLUMN = "Simplified text"

data = None

def load_data():
    global data, NUM_INSTANCES
    dataset = pd.read_csv(ENGLISH_DATASET_FILENAME, sep="	", header=None, nrows=NUM_INSTANCES)
    classes = pd.read_csv(GOLD_DATASET_FILENAME, header=None, nrows=NUM_INSTANCES)
    dataset.columns = [ID_COLUMN, "lang", TEXT_COLUMN]
    classes.columns = [ID_COLUMN, AGE_COLUMN, GENDER_COLUMN]
    dataset.drop(labels="lang", axis=1, inplace=True)
    data = dataset.join(classes[AGE_COLUMN]).join(classes[GENDER_COLUMN])
    docs = {DOC_COLUMN: []}
    i = 0
    if NUM_INSTANCES is None:
        NUM_INSTANCES = len(dataset[ID_COLUMN])
    progress_divisor = NUM_INSTANCES // 10
    print("Building Docs...")
    start = time.time()
    for _, row in data.iterrows():
        doc = nlp(row[TEXT_COLUMN])
        docs[DOC_COLUMN].append(doc)
        i += 1
        if i % progress_divisor == 0:
            print(f"{i // progress_divisor * 10}%")
    end = time.time()
    print(f"Processing time: {end - start}s")
    docs_df = pd.DataFrame.from_dict(docs)
    instances_relevant_words = {RELEVANT_WORDS_COLUMN: [], SIMPLIFIED_TEXT_COLUMN: []}
    for _, row in docs_df.iterrows():
        relevant_words = get_relevant_words(row[DOC_COLUMN])
        simple_text = get_cleaned_sentences(row[DOC_COLUMN])
        instances_relevant_words[RELEVANT_WORDS_COLUMN].append(relevant_words)
        instances_relevant_words[SIMPLIFIED_TEXT_COLUMN].append(simple_text)
    relevant_words_df = pd.DataFrame.from_dict(instances_relevant_words)
    data = data.join(docs_df).join(relevant_words_df)

def describe_data():
    class_count = Counter()
    mean_text_len = 0
    class1_words = 0
    class2_words = 0
    most_common_words = Counter()
    for _, row in data.iterrows():
        class_count.update([row[GENDER_COLUMN]])
        mean_text_len += len(row[TEXT_COLUMN])
        if row[GENDER_COLUMN] == CLASS1_VALUE:
            class1_words += len(row[DOC_COLUMN])
        else:
            class2_words += len(row[DOC_COLUMN])
        most_common_words.update(row[RELEVANT_WORDS_COLUMN])
    mean_text_len /= len(data[TEXT_COLUMN])
    mean_words = (class1_words + class2_words) / len(data[DOC_COLUMN])
    print(class_count)
    print(f"AVG CHARS IN TEXT: {mean_text_len}")
    print(f"AVG WORDS: {mean_words}")
    print(f"WORDS IN CLASS 1: {class1_words}")
    print(f"WORDS IN CLASS 2: {class2_words}")
    print("MOST COMMON WORDS")
    print(most_common_words.most_common(20))
    print("LEAST COMMON WORDS")
    print(most_common_words.most_common()[-20:])

def get_splitted_data():
    (training_data, test_data, training_classes, test_classes) = train_test_split(
        data[RELEVANT_WORDS_COLUMN],
        data[GENDER_COLUMN],
        test_size=0.30,
        random_state=1,
        stratify=data[GENDER_COLUMN]
    )
    return (training_data, test_data, training_classes, test_classes)

def get_closed_words(data):
    # TODO: Implement
    return {"time", "good", "other", "people", "way"}

def get_simple_frequency(words, data):
    frequency_matrix = {}
    for word in words:
        frequency_matrix[word] = []
    i = 0
    for row in data:
        for word in words:
            frequency_matrix[word].append(0)
        for relevant_word in row:
            if relevant_word in words:
                frequency_matrix[word][i] = 1
        i += 1
    return pd.DataFrame.from_dict(frequency_matrix)

def get_relevant_words(doc):
    relevant_words = []
    for entity in doc.ents:
        pass
        relevant_words.append(entity.text)
    for token in doc:
        is_entity_part = not token.ent_iob == 2
        is_relevant_word = True #token.pos_ in RELEVANT_POS
        if not is_entity_part and is_relevant_word:
            relevant_words.append(token.lemma_)
    return relevant_words

def get_cleaned_sentences(doc):
    text = ""
    for entity in doc.ents:
        pass
        text += " " + entity.text
    for token in doc:
        is_entity_part = not token.ent_iob == 2
        is_relevant_word = True #token.pos_ in RELEVANT_POS
        if not is_entity_part and is_relevant_word:
            text += " " + token.lemma_
    return text

def print_model_evaluation(model, test_data, test_classes, class_values):
    predictions = model.predict(test_data)
    accuracy = accuracy_score(test_classes, predictions)
    print("Accuracy score: ",accuracy)
    confusion_mat = confusion_matrix(test_classes, predictions)
    print(confusion_mat)
    report = classification_report(test_classes, predictions, target_names=class_values, zero_division=0)
    print(report)


if __name__ == "__main__":
    # Load English tokenizer, tagger, parser and NER
    #Modo turbo
    spacy.prefer_gpu()
    nlp = spacy.load("en_core_web_sm")
    load_data()
    describe_data()
    (training_data, test_data, training_classes, test_classes) = get_splitted_data()
    closed_words = get_closed_words(training_data)
    """ train_frequency = get_simple_frequency(closed_words, training_data)
    print(train_frequency.head()) """
    print(data.head())
    print(data[SIMPLIFIED_TEXT_COLUMN].head())
    CountVectorizer().fit_transform(data[SIMPLIFIED_TEXT_COLUMN])
    pipe = Pipeline([
        ('frequency', CountVectorizer()),
        ('tfidf', TfidfTransformer())]).fit(data[SIMPLIFIED_TEXT_COLUMN
    ])
    hola = pipe.transform(data[SIMPLIFIED_TEXT_COLUMN])
    print(hola.shape)
    (training_data, test_data, training_classes, test_classes) = train_test_split(
        hola,
        data[GENDER_COLUMN],
        test_size=0.30,
        #random_state=1,
        stratify=data[GENDER_COLUMN]
    )
    print(training_data)
    supervised_model = MultinomialNB().fit(training_data, training_classes)
    class_values = set(data[GENDER_COLUMN])
    print_model_evaluation(supervised_model, test_data, test_classes, class_values)