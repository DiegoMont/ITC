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

def varianza(i, n):
    s = 0
    mu = media(i, n)
    while i <= n:
        s += (i - mu)**2 * p(i)
        i += 1
    return s

print(1-binomial(0, 4, 0.8))
