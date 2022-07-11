from math import sqrt


def manhattan(x, y):
    n = len(x)
    distance = 0
    for i in range(n):
        distance += abs(x[i] - y[i])
    return distance


def euclidean(x, y):
    n = len(x)
    distance = 0
    for i in range(n):
        distance += (x[i] - y[i]) ** 2
    distance = sqrt(distance)
    return distance

def cosine_similarity(x, y):
    n = len(x)
    dot_product = 0
    x_length = 0
    y_length = 0
    for i in range(n):
        dot_product += x[i] * y[i]
        x_length += x[i] * x[i]
        y_length += y[i] * y[i]
    distance = dot_product / sqrt(x_length) / sqrt(y_length)
    return distance

data = [
    [-6,-148,-72,-35,0,-33.6,-0.627,-50],
    [1,85,66,29,0,26.6,0.351,31],
    [8,183,64,0,0,23.3,0.672,32]
]

classes = ["positive", "negative", "positive"]

print(euclidean(data[0], data[0]))
print(manhattan(data[0], data[0]))
print(cosine_similarity(data[0], data[0]))

print(euclidean(data[0], data[1]))
print(manhattan(data[0], data[1]))
print(cosine_similarity(data[0], data[1]))