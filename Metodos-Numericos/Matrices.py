import math

def imprimirMatriz(matriz):
    for row in matriz:
        for column in row:
            print(str(round(column, 4)) + "  ", end="")
        print("");
    print("");

def gaussianElimination(a):
    n = len(a)
    for k in range(n-1):
        #imprimirMatriz(a)
        for i in range(k+1, n):
            factor = a[i][k] / a[k][k]
            for j in range(k, n+1):
                a[i][j] -= factor*a[k][j]
    #imprimirMatriz(a)
    x = [0 for i in range(n)]
    x[n-1] = a[n-1][n] / a[n-1][n-1]
    for i in range(n-2, -1, -1):
        sum = a[i][n]
        for j in range(i+1, n):
            sum -= a[i][j]*x[j]
        x[i] = sum / a[i][i]
    """print("Gaussian elimination:")
    print(x)
    print("")"""
    return x

def transpose(matrix):
    rows = len(matrix)
    columns = len(matrix[0])
    transpuesta = [[0 for i in range(rows)] for j in range(columns)]
    for i in range(rows):
        for j in range(columns):
            transpuesta[j][i] = matrix[i][j]
    """print("Original matrix:")
    imprimirMatriz(matrix)
    print("Transpose matrix")
    imprimirMatriz(transpuesta)
    print("")"""
    return transpuesta

def multiplyMatrix(a, b):
    if len(a[0]) != len(b):
        print("No se pueden multiplicar estas matrices")
        return [1]
    resultado = [[0 for i in b[0]] for j in a]
    for row in range(len(a)):
        for column in range(len(b[0])):
            for i in range(len(b)):
                resultado[row][column] += a[row][i]*b[i][column]
    #imprimirMatriz(resultado)
    #print("")
    return resultado

def decompose(a):
    n = len(a)
    for k in range(n-1):
        for i in range(k+1, n):
            factor = a[i][k] / a[k][k]
            a[i][k] = factor
            for j in range(k+1, n):
                a[i][j] = a[i][j] - factor * a[k][j]
    return a

def substitute(a):
    n = len(a)
    x = [0 for i in range(n)]
    #forward substitution
    for i in range(1, n):
        sum = a[i][n]
        for j in range(i-1):
            sum = sum - a[i][j] * a[j][n]
        a[i][n] = sum
    #back substitution
    x[n-1] = a[n-1][n] / a[n-1][n-1]
    for i in range(n-2, -1, -1):
        sum = 0
        for j in range(i+1, n):
            sum = sum + a[i][j]*x[j]
        x[i] = (a[i][n]-sum)/a[i][i]
    imprimirMatriz(a)
    #print(x)
    return x

def LUDecomposition(a):
    print(substitute(decompose(a)))
    print()

def matrixInversion(a):
    n = len(a)
    a = decompose(a)
    for i in range(n):
        for j in range(n):
            if i == j:
                a[j][n] = 1
            else:
                a[j][n] = 0
        x = substitute(a)
        for j in range(n):
            a[j][i] = x[j]
    imprimirMatriz(a)
    print(x)
    print()

def gaussSeidel(a, imax = 10000, es = 0.0001, l = 1):
    n = len(a)
    x = [0 for i in range(n)]
    for i in range(n):
        dummy = a[i][i]
        for j in range(n):
            a[i][j] /= dummy
        a[i][n] /= dummy
    for i in range(n):
        sum = a[i][n]
        for j in range(n):
            if i != j:
                sum -= a[i][j] * x[j]
        x[i] = sum
    iter = 1
    sentinel = 0
    while not(sentinel == 1 or iter >= imax):
        sentinel = 1
        for i in range(n):
            old = x[i]
            sum = a[i][n]
            for j in range(n):
                if i != j:
                    sum -= a[i][j] * x[j]
            x[i] = l * sum + (1 - l) * old
            if sentinel == 1 and x[i] != 0:
                ea = abs((x[i] - old) / x[i]) * 100
                if ea > es:
                    sentinel = 0
        iter+=1
    print("Gauss-Seidel:")
    print(x)
    print()
