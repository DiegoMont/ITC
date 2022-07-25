import pandas as pd
import spacy


ENGLISH_DATASET_FILENAME = "English.txt"
GOLD_DATASET_FILENAME = "Gold-Ingles.csv"

data = []

dataset= pd.read_csv(ENGLISH_DATASET_FILENAME, sep="	", header=None)

# Load English tokenizer, tagger, parser and NER
#Modo turbo
spacy.prefer_gpu()
#nlp = spacy.load("en_core_web_trf")
nlp = spacy.load("en_core_web_sm")


for row in dataset.head(1000).iloc:
    doc = nlp(row[2])
    print(doc.text[:20])
    data.append([row[0],doc])

# doc = nlp(dataset.iat[0, 2])
# print(doc)
