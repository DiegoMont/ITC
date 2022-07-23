from numpy import average
import pandas as pd
from sklearn.cluster import AgglomerativeClustering, KMeans
import matplotlib.pyplot as plt
import numpy as np
from sklearn.decomposition import PCA
import collections

from sklearn.metrics import silhouette_score


MAY_DATASET_FILENAME = "cepea-mayo.csv"
JULY_DATASET_FILENAME = "cepea-julio.csv"
COLUMN_NAMES = ["Sexo", "Carrera", "MS", "MP", "ML", "EsS", "EsP", "EsL", "Mes", "Clase"]
COLUMN_NAMES2 = ["Sexo", "Carrera", ] + [str(i) for i in range(1, 43)]
CLASSES = ["ES", "EP", "EL"]

data = None
assesment_results = None

def load_dataset():
    global data
    may = pd.read_csv(MAY_DATASET_FILENAME)
    may.drop("id", inplace=True, axis=1)
    may.columns = COLUMN_NAMES2
    july = pd.read_csv(JULY_DATASET_FILENAME)
    july.drop("Marca temporal", inplace=True, axis=1)
    july.columns = COLUMN_NAMES2
    july.bfill(inplace=True)
    may.insert(len(COLUMN_NAMES2), "Mes", "MAYO")
    july.insert(len(COLUMN_NAMES2), "Mes", "JULIO")
    data = pd.concat([may, july])
    data[COLUMN_NAMES2[1]] = data[COLUMN_NAMES2[1]].str.upper()

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
        _, bins = np.histogram(assesment_results[COLUMN_NAMES[i]], bins=25)
        axis = plots[i-2]
        axis.hist(es_instances[COLUMN_NAMES[i]], bins=bins, alpha=0.5)
        axis.hist(ep_instances[COLUMN_NAMES[i]], bins=bins, alpha=0.5)
        axis.hist(el_instances[COLUMN_NAMES[i]], bins=bins, alpha=0.5)
        axis.set_title(COLUMN_NAMES[i])
    plots[0].legend(CLASSES, loc="best")
    plt.show()

def plot_month_groups(columns_to_use):
    may_instances = assesment_results.query("Mes == 'MAYO'")
    july_instances = assesment_results.query("Mes == 'JULIO'")
    print(may_instances.head())
    pca = PCA(n_components=2)
    pca.fit(may_instances.get(columns_to_use))
    plain_may = pca.transform(may_instances.get(columns_to_use))
    plain_july = pca.transform(july_instances.get(columns_to_use))
    plt.scatter(plain_may[:, 0], plain_may[:, 1], c="Pink", alpha=0.5)
    plt.scatter(plain_july[:, 0], plain_july[:, 1], c="Orange")
    plt.show()

def make_month_clusters(columns_to_use):
    kmeans = KMeans(n_clusters=2)
    #print(assesment_results.head())
    train_data = data.get(columns_to_use)
    pca = PCA(n_components=2)
    pca.fit(train_data)
    undimensioned_data = pca.transform(train_data)
    kmeans.fit(undimensioned_data)
    print(kmeans.labels_)
    # cluster 0
    mayo_instances = undimensioned_data[assesment_results["Mes"] == "MAYO"]
    july_instances = undimensioned_data[assesment_results["Mes"] == "JULIO"]
    undimensioned_data = np.c_[undimensioned_data, data[COLUMN_NAMES[8]]]
    instances_cluster_0 = undimensioned_data[kmeans.labels_ == 0]
    instances_cluster_1 = undimensioned_data[kmeans.labels_ == 1]
    print(COLUMN_NAMES[-2])
    plt.scatter(mayo_instances[:, 0], mayo_instances[:, 1], c="Pink", alpha=0.75)
    plt.scatter(july_instances[:, 0], july_instances[:, 1], c="Red", alpha=0.75)
    plt.title("Instancias de Mayo y Julio (excluyendo columna de MP)")
    plt.legend(["Mayo", "Julio"], loc="best")
    plt.show()
    month_counts = [0, 0, 0, 0]
    for instance in instances_cluster_0:
        if instance[2] == 'MAYO':
            month_counts[0] += 1
        else:
            month_counts[1] += 1
    for instance in instances_cluster_1:
        if instance[2] == 'MAYO':
            month_counts[2] += 1
        else:
            month_counts[3] += 1
    print(month_counts)
    plt.scatter(instances_cluster_0[:, 0], instances_cluster_0[:, 1], c="Red", alpha=0.75)
    plt.scatter(instances_cluster_1[:, 0], instances_cluster_1[:, 1], c="Blue", alpha=0.75)
    plt.scatter(kmeans.cluster_centers_[:, 0], kmeans.cluster_centers_[:, 1], c="Green")
    plt.title("K-means (K = 2)[Utilizando respuestas de preguntas]")
    plt.legend(["Cluster 1", "Cluster 2", "Centroids"], loc="best")
    month_clusters_metrics(month_counts)
    plt.show()
    score = silhouette_score(train_data, kmeans.labels_)
    print("Silhouette", score)

def month_clusters_metrics(month_counts):
    accuracy = (month_counts[0] + month_counts[1]) / (month_counts[0] + month_counts[1] + month_counts[2] + month_counts[3])
    precision = month_counts[0] / (month_counts[0] + month_counts[1])
    recall = month_counts[0] / (month_counts[0] + month_counts[2])
    fscore = 2 * ((precision * recall)/ (precision + recall))
    print(f"Accuracy: {accuracy}\nPrecision: {precision}\nRecall: {recall}\nF1-score: {fscore}")


def plot_classes_groups(columns_to_use):
    es_instances = assesment_results.query("Clase == 'ES'")
    ep_instances = assesment_results.query("Clase == 'EP'")
    el_instances = assesment_results.query("Clase == 'EL'")
    print(es_instances.head())
    pca = PCA(n_components=2)
    pca.fit(es_instances.get(columns_to_use))
    plain_es = pca.transform(es_instances.get(columns_to_use))
    plain_ep = pca.transform(ep_instances.get(columns_to_use))
    plain_el = pca.transform(el_instances.get(columns_to_use))
    plt.scatter(plain_es[:, 0], plain_es[:, 1], c="Pink", alpha=0.5)
    plt.scatter(plain_ep[:, 0], plain_ep[:, 1], c="Orange")
    plt.scatter(plain_el[:, 0], plain_el[:, 1], c="Yellow")
    plt.show()

def make_classes_clusters(columns_to_use):
    kmeans = KMeans(n_clusters=3)
    train_data = assesment_results.get(columns_to_use)
    kmeans.fit(train_data)
    pca = PCA(n_components=2)
    pca.fit(train_data)
    undimensioned_data = pca.transform(train_data)
    undimensioned_data = np.c_[undimensioned_data, assesment_results[COLUMN_NAMES[-1]]]
    undimensioned_centers = pca.transform(kmeans.cluster_centers_)
    instances_cluster_0 = undimensioned_data[kmeans.labels_ == 0]
    instances_cluster_1 = undimensioned_data[kmeans.labels_ == 1]
    instances_cluster_2 = undimensioned_data[kmeans.labels_ == 2]
    plt.scatter(instances_cluster_0[:, 0], instances_cluster_0[:, 1], c="Red", alpha=0.75)
    plt.scatter(instances_cluster_1[:, 0], instances_cluster_1[:, 1], c="Blue", alpha=0.75)
    plt.scatter(instances_cluster_2[:, 0], instances_cluster_2[:, 1], c="Brown", alpha=0.75)
    plt.scatter(undimensioned_centers[:, 0],undimensioned_centers[:, 1], c="Green")
    plt.title("K-means (K = 3)[Atributos: EsP, MP, EsS]")
    plt.legend(["Cluster 1", "Cluster 2", "Cluster 3", "Centroids"], loc="best")
    print_classes_metrics(instances_cluster_0, instances_cluster_1, instances_cluster_2)
    score = silhouette_score(train_data, kmeans.labels_)
    print("Silhouette", score)
    plt.show()

def print_classes_metrics(instances_cluster_0, instances_cluster_1, instances_cluster_2):
    classes_counts = [0, 0, 0, 0, 0, 0, 0, 0, 0]
    for instance in instances_cluster_0:
        #print(instance[2])
        if instance[2] == 'ES':
            classes_counts[0] += 1
        elif instance[2] == 'EP':
            classes_counts[1] += 1
        else:
            classes_counts[2] += 1
    for instance in instances_cluster_1:
        if instance[2] == 'ES':
            classes_counts[3] += 1
        elif instance[2] == 'EP':
            classes_counts[4] += 1
        else:
            classes_counts[5] += 1
    for instance in instances_cluster_2:
        if instance[2] == 'ES':
            classes_counts[6] += 1
        elif instance[2] == 'EP':
            classes_counts[7] += 1
        else:
            classes_counts[8] += 1
    print(classes_counts)
    #me interesa ES
    tp = classes_counts[0]
    fp = classes_counts[1] + classes_counts[2]
    fn = classes_counts[3] + classes_counts[6]
    tn = classes_counts[4] + classes_counts[5] + classes_counts[7] + classes_counts[8]
    print(f"TP {tp} FP {fp}")
    print(f"FN {fn} TN {tn}")
    accuracy = (tp + tn) / (tp + fp + tn + fn)
    precision = tp / (tp + fp)
    recall = tp / (tp + fn)
    fscore = 2 * ((precision * recall)/ (precision + recall))
    print(f"Accuracy: {accuracy}\nPrecision: {precision}\nRecall: {recall}\nF1-score: {fscore}")

def hierarchy_months_cluster(columns_to_use):
    agg = AgglomerativeClustering(n_clusters=2)
    train_data = data.get(columns_to_use)
    pca = PCA(n_components=2)
    pca.fit(train_data)
    undimensioned_data = pca.transform(train_data)
    model = agg.fit_predict(undimensioned_data)
    # print(model)
    instances_cluster_0 = np.c_[undimensioned_data, assesment_results[COLUMN_NAMES[8]]][agg.labels_ == 0]
    instances_cluster_1 = np.c_[undimensioned_data, assesment_results[COLUMN_NAMES[8]]][agg.labels_ == 1]
    plt.scatter(instances_cluster_0[:, 0], instances_cluster_0[:, 1], c="Red", alpha=0.75)
    plt.scatter(instances_cluster_1[:, 0], instances_cluster_1[:, 1], c="Blue", alpha=0.75)
    plt.title("Agrupamiento jerárquico (K = 2) [Utilizando respuestas de preguntas]")
    plt.legend(["Cluster 1", "Cluster 2", "Centroids"], loc="best")
    month_counts = [0, 0, 0, 0]
    for instance in instances_cluster_0:
        if instance[2] == 'MAYO':
            month_counts[0] += 1
        else:
            month_counts[1] += 1
    for instance in instances_cluster_1:
        if instance[2] == 'MAYO':
            month_counts[2] += 1
        else:
            month_counts[3] += 1
    month_clusters_metrics(month_counts)
    plt.show()
    score = silhouette_score(train_data, model)
    print("Silhouette", score)

def hierarchy_classes_cluster(columns_to_use):
    agg = AgglomerativeClustering(n_clusters=3)
    train_data = assesment_results.get(columns_to_use)
    pca = PCA(n_components=2)
    pca.fit(train_data)
    undimensioned_data = pca.transform(train_data)
    model = agg.fit_predict(undimensioned_data)
    # print(model)
    instances_cluster_0 = np.c_[undimensioned_data, assesment_results[COLUMN_NAMES[-1]]][agg.labels_ == 0]
    instances_cluster_1 = np.c_[undimensioned_data, assesment_results[COLUMN_NAMES[-1]]][agg.labels_ == 1]
    instances_cluster_2 = np.c_[undimensioned_data, assesment_results[COLUMN_NAMES[-1]]][agg.labels_ == 2]
    plt.scatter(instances_cluster_0[:, 0], instances_cluster_0[:, 1], c="Red", alpha=0.75)
    plt.scatter(instances_cluster_1[:, 0], instances_cluster_1[:, 1], c="Blue", alpha=0.75)
    plt.scatter(instances_cluster_2[:, 0], instances_cluster_2[:, 1], c="Brown", alpha=0.75)
    plt.title("Agrupamiento jerárquico (K = 3) [Atributos: EsP, MP, EsS]")
    plt.legend(["Cluster 1", "Cluster 2", "Cluster 3", "Centroids"], loc="best")
    print_classes_metrics(instances_cluster_0, instances_cluster_1, instances_cluster_2)
    score = silhouette_score(train_data, model)
    print("Silhouette", score)
    plt.show()

if __name__ == "__main__":
    load_dataset()
    calculate_assesment_results()
    # plot_data()
    # prueba mayo julio
    #make_month_clusters([COLUMN_NAMES[2], COLUMN_NAMES[4], COLUMN_NAMES[5], COLUMN_NAMES[6], COLUMN_NAMES[7]])
    make_month_clusters([COLUMN_NAMES2[i] for i in range(2, 44)])
    #make_classes_clusters([COLUMN_NAMES2[i] for i in range(2, 44)])
    # plot_month_groups([COLUMN_NAMES[2], COLUMN_NAMES[4], COLUMN_NAMES[5], COLUMN_NAMES[6], COLUMN_NAMES[7]])
    # prueba de enfoque de aprendizaje
    #make_classes_clusters([COLUMN_NAMES[2],COLUMN_NAMES[3], COLUMN_NAMES[4], COLUMN_NAMES[5], COLUMN_NAMES[6], COLUMN_NAMES[7]])
    # plot_classes_groups([COLUMN_NAMES[2],COLUMN_NAMES[3], COLUMN_NAMES[4], COLUMN_NAMES[5], COLUMN_NAMES[6], COLUMN_NAMES[7]])
    #hierarchy_months_cluster([COLUMN_NAMES2[i] for i in range(2, 44)])
    #hierarchy_classes_cluster([COLUMN_NAMES2[i] for i in range(2, 44)])
    #hierarchy_classes_cluster([COLUMN_NAMES[i] for i in range(2, 8)])
    #make_classes_clusters([COLUMN_NAMES[3], COLUMN_NAMES[5], COLUMN_NAMES[6]])
    #hierarchy_classes_cluster([COLUMN_NAMES[3], COLUMN_NAMES[5], COLUMN_NAMES[6]])

    result = collections.Counter(assesment_results.iloc[:,1])
    print(result)
    print(len(result))