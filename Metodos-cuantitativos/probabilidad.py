def factorial(n):
    return 1 if n < 2 else n * factorial(n-1)

def combinaciones(n, x):
    return factorial(n) / factorial(x) / factorial(n-x)


def binomial(x, n, p):
    q = 1 - p
    return combinaciones(n, x) * p**x * q**(n-x)
