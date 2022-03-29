import matplotlib.pyplot as plt

def factorial(n):
    return 1 if n < 2 else n * factorial(n-1)

def combinaciones(n, x):
    return factorial(n) / factorial(x) / factorial(n-x)

def binomial(x, n, p):
    q = 1 - p
    return combinaciones(n, x) * p**x * q**(n-x)


def poisson(x, mu):
    return math.exp(-mu) * mu**x / factorial(x)

def binomial_negativa(x, r, p):
    return combinaciones(x+r-1, r-1) * p**r * (1-p)**x

def p(x):
    probabilidad = [1/8, 3/8, 3/8, 1/8]
    return probabilidad[x]

def media(i, n):
    mu = 0
    while i <= n:
        mu += p(i) * i
        i += 1
    return mu

def mediaValores(lista):
    media = 0;
    for v in lista:
        media += v
    media /= len(lista)
    return media

def varianza(i, n):
    s = 0
    mu = media(i, n)
    while i <= n:
        s += (i - mu)**2 * p(i)
        i += 1
    return s

def pruebaChiCuadrada(datos):
    frecuencias_observadas = [21, 15, 8, 6]
    frecuencias_esperadas = [26.4, 12.45, 5.9, 5.25]
    x20 = 0
    for i in range(len(frecuencias_esperadas)):
        x20 += ((frecuencias_observadas[i] - frecuencias_esperadas[i]) ** 2) / frecuencias_esperadas[i]
    return x20

muestra = [8.223, 2.23, 2.92, 0.761, 1.064, 0.836, 3.81, 0.968, 4.49, 0.186, 2.634, 1.624, 0.333, 1.514, 2.782, 4.778, 1.507, 4.025, 1.064, 3.246, 0.406, 2.343, 0.538, 5.088, 5.587, 0.517, 1.458, 0.234, 1.401, 0.685, 2.33, 0.774, 3.323, 0.294, 1.725, 2.563, 0.023, 3.334, 3.491, 1.267, 0.511, 0.225, 2.325, 2.921, 1.702, 6.426, 3.214, 7.514, 0.334, 1.849]

valor_esperado = mediaValores(muestra)
lmbda = 1 / valor_esperado
print("lambda", lmbda)
print(pruebaChiCuadrada(muestra))
