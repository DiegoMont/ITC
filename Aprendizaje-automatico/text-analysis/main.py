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

data = None

def load_data():
    global data
    dataset = pd.read_csv(ENGLISH_DATASET_FILENAME, sep="	", header=None, nrows=NUM_INSTANCES)
    classes = pd.read_csv(GOLD_DATASET_FILENAME, header=None, nrows=NUM_INSTANCES)
    dataset.columns = [ID_COLUMN, "lang", TEXT_COLUMN]
    classes.columns = [ID_COLUMN, AGE_COLUMN, GENRE_COLUMN]
    dataset.drop(labels="lang", axis=1, inplace=True)
    data = dataset.join(classes[AGE_COLUMN]).join(classes[GENRE_COLUMN])
    docs = {"doc": []}
    i = 0
    progress_divisor = NUM_INSTANCES // 10
    print("Building Docs...")
    start = time.time()
    for _, row in data.iterrows():
        doc = nlp(row[TEXT_COLUMN])
        docs["doc"].append(doc)
        i += 1
        if i % progress_divisor == 0:
            print(f"{i // progress_divisor * 10}%")
    end = time.time()
    print(f"Processing time: {end - start}s")
    docs_df = pd.DataFrame.from_dict(docs)
    data = data.join(docs_df)


if __name__ == "__main__":
    # Load English tokenizer, tagger, parser and NER
    #Modo turbo
    spacy.prefer_gpu()
    nlp = spacy.load("en_core_web_sm")
    load_data()