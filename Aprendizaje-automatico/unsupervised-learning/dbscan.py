from typing import Counter
from sklearn.cluster import DBSCAN
from sklearn.decomposition import PCA
from numpy import average
import pandas as pd
import matplotlib.pyplot as plt
import itertools
import numpy as np


MAY_DATASET_FILENAME = "cepea-mayo.csv"
JULY_DATASET_FILENAME = "cepea-julio.csv"
COLUMN_NAMES = ["Sexo", "Carrera", "MS", "MP", "ML", "EsS", "EsP", "EsL", "Mes", "Clase"]
CLASSES = ["ES", "EP", "EL"]

data = None
assesment_results = None

def load_dataset():
    global data
    COLUMN_NAMES = ["Sexo", "Carrera", ] + [str(i) for i in range(1, 43)]
    may = pd.read_csv(MAY_DATASET_FILENAME)
    may.drop("id", inplace=True, axis=1)
    may.columns = COLUMN_NAMES
    july = pd.read_csv(JULY_DATASET_FILENAME)
    july.drop("Marca temporal", inplace=True, axis=1)
    july.columns = COLUMN_NAMES
    july.bfill(inplace=True)
    may.insert(len(COLUMN_NAMES), "Mes", "MAYO")
    july.insert(len(COLUMN_NAMES), "Mes", "JULIO")
    data = pd.concat([may, july])
    data[COLUMN_NAMES[1]] = data[COLUMN_NAMES[1]].str.upper()

def calculate_assesment_results():
    global assesment_results
    assesment_results = pd.DataFrame(columns=COLUMN_NAMES)
    scores = {COLUMN_NAMES[0]: [], COLUMN_NAMES[1]: [], COLUMN_NAMES[2]: [], COLUMN_NAMES[3]: [], COLUMN_NAMES[4]: [], COLUMN_NAMES[5]: [], COLUMN_NAMES[6]: [], COLUMN_NAMES[7]: [], COLUMN_NAMES[8]: [], COLUMN_NAMES[9]: []}
    for row in data.iloc:
        scores[COLUMN_NAMES[0]].append(row[0])
        scores[COLUMN_NAMES[1]].append(row[1])
        scores[COLUMN_NAMES[2]].append((row[2] + row[8] + row[14] + row[20] + row[26] + row[32] + row[38]) / 7)
        scores[COLUMN_NAMES[3]].append((row[3] + row[9] + row[15] + row[21] + row[27] + row[33] + row[39]) / 7)
        scores[COLUMN_NAMES[4]].append((row[4] + row[10] + row[16] + row[22] + row[28] + row[34] + row[40]) / 7)
        scores[COLUMN_NAMES[5]].append((row[5] + row[11] + row[17] + row[23] + row[29] + row[35] + row[41]) / 7)
        scores[COLUMN_NAMES[6]].append((row[6] + row[12] + row[18] + row[24] + row[30] + row[36] + row[42]) / 7)
        scores[COLUMN_NAMES[7]].append((row[7] + row[13] + row[19] + row[25] + row[31] + row[37] + row[43]) / 7)
        scores[COLUMN_NAMES[8]].append(row[44])
        es = average([scores[COLUMN_NAMES[2]][-1], scores[COLUMN_NAMES[5]][-1]])
        ep = average([scores[COLUMN_NAMES[3]][-1], scores[COLUMN_NAMES[6]][-1]])
        el = average([scores[COLUMN_NAMES[4]][-1], scores[COLUMN_NAMES[7]][-1]])
        if es > ep and es > el:
            klass = "ES"
        elif ep > es and ep > el:
            klass = "EP"
        else:
            klass = "EL"
        scores[COLUMN_NAMES[9]].append(klass)
    new_dataframe = pd.DataFrame.from_dict(scores)
    assesment_results = pd.concat([assesment_results, new_dataframe])
    assesment_results[COLUMN_NAMES[1]] = assesment_results[COLUMN_NAMES[1]].astype('category')
    assesment_results[COLUMN_NAMES[0]] = assesment_results[COLUMN_NAMES[0]].astype('category')
    categorical_columns = assesment_results.select_dtypes('category').columns
    assesment_results[categorical_columns] = assesment_results[categorical_columns].apply(lambda x: pd.factorize(x)[0])
    assesment_results[COLUMN_NAMES[1]] = assesment_results[COLUMN_NAMES[1]].astype(int) 
    assesment_results[COLUMN_NAMES[0]] = assesment_results[COLUMN_NAMES[0]].astype(int) 

def plot_classes(undimensioned_data):
    es_instances = undimensioned_data[assesment_results["Mes"] == "MAYO"]
    el_instances = undimensioned_data[assesment_results["Mes"] == "JULIO"]
    ep_instances = undimensioned_data[assesment_results["Clase"] == "EP"]
    plt.scatter(es_instances[:, 0], es_instances[:, 1], c="Green", alpha=0.75)
    plt.scatter(ep_instances[:, 0], ep_instances[:, 1], c="Red", alpha=0.75)
    #plt.scatter(el_instances[:, 0], el_instances[:, 1], c="Orange", alpha=0.5)
    plt.title("Meses (mayo, julio)")
    plt.legend(["Mayo", "Julio"], loc="best")
    plt.show()

def dbscan(columns_to_use, epsilon=0.5, samples=5):
    print(epsilon, samples)
    dbscan = DBSCAN(eps=epsilon, min_samples=samples)
    train_data = assesment_results.get(columns_to_use)
    pca = PCA(n_components=2)
    pca.fit(train_data)
    undimensioned_data = pca.transform(train_data)
    plot_classes(undimensioned_data)
    dbscan.fit_predict(undimensioned_data)
    cluster_ids = np.unique(dbscan.labels_)
    found_instances = Counter(dbscan.labels_)
    print(cluster_ids)
    clusters = []
    for cluster_id in cluster_ids:
        clusters.append(undimensioned_data[dbscan.labels_ == cluster_id])
    for cluster in clusters:
        plt.scatter(cluster[:, 0], cluster[:, 1], alpha=0.75)
    if len(cluster_ids) > 5:
        plt.show()
    #plt.scatter(instances_cluster_0[:, 0], instances_cluster_0[:, 1], c="Red", alpha=0.75)
    #plt.scatter(instances_cluster_1[:, 0], instances_cluster_1[:, 1], c="Blue", alpha=0.75)
    #plt.scatter(instances_cluster_2[:, 0], instances_cluster_2[:, 1], c="Brown", alpha=0.75)
    #plt.scatter(instances_cluster_3[:, 0], instances_cluster_3[:, 1], c="Orange", alpha=0.75)
    #plt.show()


load_dataset()
calculate_assesment_results()
COLUMNS_INDEX = range(2, 8)
#for r in range(2, len(COLUMNS_INDEX)+1):
columns_to_use = [COLUMN_NAMES[i] for i in range(2, 8)]
dbscan(columns_to_use)
""" for r in range(2, len(COLUMNS_INDEX)+1):
    for combination in itertools.combinations(COLUMNS_INDEX, r):
        columns_to_use = []
        for index in combination:
            columns_to_use.append(COLUMN_NAMES[index])
        print(columns_to_use)
        dbscan(columns_to_use) """