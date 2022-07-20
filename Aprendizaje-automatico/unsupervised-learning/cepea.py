from numpy import average
import pandas as pd
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt
import numpy as np
from sklearn.decomposition import PCA


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

def plot_data():
    es_instances = assesment_results.query("Clase == 'ES'")
    ep_instances = assesment_results.query("Clase == 'EP'")
    el_instances = assesment_results.query("Clase == 'EL'")
    print("ES:", es_instances.shape[0])
    print("EP:", ep_instances.shape[0])
    print("EL:", el_instances.shape[0])
    _, axes = plt.subplots(3, 2)
    plots = []
    for a in axes:
        for plot in a:
            plots.append(plot)
    for i in range(2, 8):
        _, bins = np.histogram(assesment_results[COLUMN_NAMES[i]], bins=20)
        axis = plots[i-2]
        axis.hist(es_instances[COLUMN_NAMES[i]], bins=bins, alpha=0.5)
        axis.hist(ep_instances[COLUMN_NAMES[i]], bins=bins, alpha=0.5)
        axis.hist(el_instances[COLUMN_NAMES[i]], bins=bins, alpha=0.5)
        axis.set_title(COLUMN_NAMES[i])
    plots[0].legend(CLASSES, loc="best")
    plt.show()

def make_clusters():
    kmeans = KMeans(n_clusters=2)
    print(assesment_results.head())
    train_data = assesment_results.get([COLUMN_NAMES[2], COLUMN_NAMES[3], COLUMN_NAMES[4], COLUMN_NAMES[5], COLUMN_NAMES[6], COLUMN_NAMES[7]])
    kmeans.fit(train_data)
    pca = PCA(n_components=2)
    pca.fit(train_data)
    undimensioned_data = pca.transform(train_data)
    undimensioned_centers = pca.transform(kmeans.cluster_centers_)
    print(kmeans.cluster_centers_)
    # cluster 0
    instances_cluster_0 = undimensioned_data[kmeans.labels_ == 0]
    plt.scatter(instances_cluster_0[:, 0], instances_cluster_0[:, 1])
    instances_cluster_1 = undimensioned_data[kmeans.labels_ == 1]
    plt.scatter(instances_cluster_1[:, 0], instances_cluster_1[:, 1])
    plt.scatter(undimensioned_centers[:, 0],undimensioned_centers[:, 1])
    plt.show()

if __name__ == "__main__":
    load_dataset()
    calculate_assesment_results()
    plot_data()
    make_clusters()