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
