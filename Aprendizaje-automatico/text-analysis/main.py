from collections import Counter
import pandas as pd
import spacy
import time


ENGLISH_DATASET_FILENAME = "English.txt"
GOLD_DATASET_FILENAME = "Gold-Ingles.csv"
NUM_INSTANCES = 100
ID_COLUMN = "ID"
AGE_COLUMN = "Age"
GENRE_COLUMN = "Genre"
TEXT_COLUMN = "Text"
DOC_COLUMN = "Spacy Doc"
CLASS1_VALUE = "male"
RELEVANT_POS = ["ADJ", "NOUN", "PROPN"]

data = None

def load_data():
    global data
    dataset = pd.read_csv(ENGLISH_DATASET_FILENAME, sep="	", header=None, nrows=NUM_INSTANCES)
    classes = pd.read_csv(GOLD_DATASET_FILENAME, header=None, nrows=NUM_INSTANCES)
    dataset.columns = [ID_COLUMN, "lang", TEXT_COLUMN]
    classes.columns = [ID_COLUMN, AGE_COLUMN, GENRE_COLUMN]
    dataset.drop(labels="lang", axis=1, inplace=True)
    data = dataset.join(classes[AGE_COLUMN]).join(classes[GENRE_COLUMN])
    docs = {DOC_COLUMN: []}
    i = 0
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
    data = data.join(docs_df)

def describe_data():
    class_count = Counter()
    mean_text_len = 0
    class1_words = 0
    class2_words = 0
    most_common_words = Counter()
    for _, row in data.iterrows():
        class_count.update([row[GENRE_COLUMN]])
        mean_text_len += len(row[TEXT_COLUMN])
        if row[GENRE_COLUMN] == CLASS1_VALUE:
            class1_words += len(row[DOC_COLUMN])
        else:
            class2_words += len(row[DOC_COLUMN])
        relevant_words = get_relevant_words(row[DOC_COLUMN])
        most_common_words.update(relevant_words)
    mean_text_len /= len(data[TEXT_COLUMN])
    mean_words = (class1_words + class2_words) / len(data[DOC_COLUMN])
    print(class_count)
    print(f"Avg chars in text: {mean_text_len}")
    print(f"Avg words: {mean_words}")
    print(most_common_words.most_common(20))

def get_relevant_words(doc):
    relevant_words = []
    for entity in doc.ents:
        relevant_words.append(entity.text)
    for token in doc:
        is_entity_part = not token.ent_iob == 2
        is_relevant_word = token.pos_ in RELEVANT_POS
        if not is_entity_part and is_relevant_word:
            relevant_words.append(token.lemma_)
    return relevant_words



if __name__ == "__main__":
    # Load English tokenizer, tagger, parser and NER
    #Modo turbo
    spacy.prefer_gpu()
    nlp = spacy.load("en_core_web_sm")
    load_data()
    describe_data()