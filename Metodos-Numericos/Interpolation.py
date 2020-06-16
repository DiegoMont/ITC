import Matrices

gaussianElimination = Matrices.gaussianElimination
imprimirMatriz = Matrices.imprimirMatriz

def polynomialInterpolation(x, y, xi):
    yint = [0 for i in y]
    ea = [0 for i in y]
    n = len(x)
    fdd = [[0 for i in x] for j in y]
    for i in range(n):
        fdd[i][0] = y[i]
    for j in range(1, n):
        for i in range(n - j):
            fdd[i][j] = (fdd[i+1][j-1] - fdd[i][j-1])/(x[i+j] - x[i])
    xterm = 1
    yint[0] = fdd[0][0]
    for order in range(1, n):
        xterm *= (xi - x[order-1])
        yint2 = yint[order-1] + fdd[0][order] * xterm
        ea[order-1] = yint2 - yint[order-1]
        yint[order] = yint2
    print("xi:", xi)
    print("yint:", yint[n-1])

def linearSplines(x, y, x1):
    n = len(x) - 1
    for i in range(n):
        if x[i] <= x1 and x1 <= x[i+1]:
            m = (y[i+1] - y[i]) / (x[i+1] - x[i])
            result = y[i] + m*(x1-x[i])
            break
    print("Linear splines\n", "f(" + str(x1) + ")=", result)

def cubicSplines(x, y, w):
    n = len(x)
    lhs = [[0 for j in range(n-1)] for i in range(n-2)]
    for i in range(1, n-1):
        for j in range(1, n-1):
            if i-1 == j-1:
                lhs[i-1][j-1] = 2 * (x[i+1] - (0 if i-1 < 0 else x[i-1]))
            elif i-1 == j:
                lhs[i-1][j-1] = (x[i] - (0 if i-1 < 0 else x[i-1]))
            elif i == j-1:
                lhs[i-1][j-1] = (x[i+1] - x[i])
        lhs[i-1][n-2] = 6*(y[i+1] - y[i]) / (x[i+1] - x[i]) + 6*(y[i-1] - y[i]) / (x[i] - x[i-1])
    #imprimirMatriz(lhs);
    secondDerivatives = gaussianElimination(lhs)
    fs = [0 for i in range(n)]
    for i in range(0, n-2):
        fs[i+1] = secondDerivatives[i]
    i=0
    for j in range(1, n):
        if x[j-1] <= w and w <= x[j]:
            i = j
            break
    termino1 = fs[i-1]*(x[i]-w)**3/(6*(x[i]-x[i-1]))
    termino2 = fs[i]*(w - x[i-1])**3/(6*(x[i] - x[i-1]))
    termino3 = (y[i-1]/(x[i]-x[i-1]) - fs[i-1]*(x[i]-x[i-1])/6)*(x[i] - w)
    termino4 = (y[i]/(x[i] - x[i-1]) - fs[i]*(x[i] - x[i-1]) / 6)*(w - x[i-1])
    f = termino1 + termino2 + termino3 + termino4
    print("Cubic splines")
    print("f(" + str(w) + ")=", f)
    return f
