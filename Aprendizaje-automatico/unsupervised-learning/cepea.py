from numpy import average
import pandas as pd
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt


MAY_DATASET_FILENAME = "cepea-mayo.csv"
JULY_DATASET_FILENAME = "cepea-julio.csv"
COLUMN_NAMES = ["Sexo", "Carrera", "MS", "MP", "ML", "EsS", "EsP", "EsL", "Mes", "Clase"]
CLAASSES = ["ES", "EP", "EL"]

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


if __name__ == "__main__":
    load_dataset()
    calculate_assesment_results()
    kmeans = KMeans(n_clusters=2)
    print(assesment_results.head())
    train_data = assesment_results.iloc[:, 2:8]
    kmeans.fit(train_data)
    print(kmeans.cluster_centers_)
    #print(train_data[:0, 0])
    """ print(train_data)
    train_data[0]
    train_data[:, 1] """
    print(kmeans.cluster_centers_)
    plt.scatter(assesment_results[COLUMN_NAMES[2]], assesment_results[COLUMN_NAMES[3]], kmeans.labels_)
    plt.scatter(kmeans.cluster_centers_[:, 0], kmeans.cluster_centers_[:, 1])
    plt.show()
    #graph = assesment_results.plot.scatter(x=COLUMN_NAMES[2], y=COLUMN_NAMES[4]).show()