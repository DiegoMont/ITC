import math
import Matrices

gaussianElimination = Matrices.gaussianElimination
multiplyMatrix = Matrices.multiplyMatrix
transpose = Matrices.transpose

def linearRegression(x, y):
    n = len(x)
    sumx = 0
    sumxy = 0
    st = 0
    sumy = 0
    sumx2 = 0
    sr = 0
    for i in range(n):
        sumx += x[i]
        sumy += y[i]
        sumxy += x[i]*y[i]
        sumx2 += x[i]**2
    xm = sumx/n
    ym = sumy/n
    a1 = (n*sumxy - sumx*sumy) / (n*sumx2 - sumx**2)
    a0 = ym - a1*xm
    for i in range(n):
        st += (y[i] - ym)**2
        sr += (y[i] - a1*x[i] - a0)**2
    syx = (sr / (n-2))**0.5
    r2 = (st - sr) / st
    """print("Linear regression model")
    print("a0:", a0, "a1:", a1)
    print("sy/x:", round(syx, 5))
    print("r2:", round(r2, 5))
    print("")"""
    return [a0, a1]

def polynomialRegression(x, y, grade):
    n = len(x)
    sums = [[0 for x in range(grade+2)] for i in range(grade+1)]
    st = 0
    sr = 0
    """
    n  x1 x2 y
    x1 x2 x3 xy
    x2 x3 x4 x2y
    """
    for i in range(n):
        for row in range(grade+1):
            for column in range(grade+2):
                if column == grade+1:
                    sums[row][column]+=x[i]**row*y[i]
                else:
                    sums[row][column]+=x[i]**(column+row)
    #imprimirMatriz(sums)
    a = gaussianElimination(sums)
    ym = sums[0][grade+1] / n
    for i in range(n):
        st += (y[i] - ym)**2
        suma = y[i]
        for j in range(len(a)):
            suma-= a[j]*x[i]**j
        sr += suma**2
    syx = (sr / (n-grade-1))**0.5
    r2 = (st - sr) / st
    print("Polynomial regression model of grade", grade)
    print(a)
    print("sy/x:", round(syx, 5))
    print("r2:", round(r2, 5))
    print("")

def exponentialModel(x, y):
    n = len(x)
    lny = [0 for i in x]
    for i in range(n):
        lny[i] = math.log(y[i])
    a = linearRegression(x, lny)
    #Cambiar coeficientes a modelo exponencial
    a[0] = math.exp(a[0])
    sr = 0
    st = 0
    ym = 0
    for i in range(n):
        ym += y[i]
        sr += (y[i] - (a[0]*math.exp(a[1]*x[i])))**2
    ym /= n
    for valor in y:
        st += (valor-ym)**2
    syx = (sr / (n-2))**0.5
    r2 = (st - sr) / st
    print("Exponential model regression:")
    print("a1:", a[0], "  B:", a[1])
    print("sy/x:", round(syx, 5))
    print("r2:", round(r2, 5))
    print("")

def powerModel(x, y):
    n = len(x)
    logy = [0 for i in x]
    logx = [0 for i in x]
    for i in range(n):
        logy[i] = math.log(y[i], 10)
        logx[i] = math.log(x[i], 10)
    a = linearRegression(logx, logy)
    #Cambiar coeficientes a power model
    a[0] = 10**a[0]
    sr = 0
    st = 0
    ym = 0
    for i in range(n):
        ym += y[i]
        sr += (y[i] - (a[0] * x[i]**a[1]))**2
    ym /= n
    for valor in y:
        st += (valor-ym)**2
    syx = (sr / (n-2))**0.5
    r2 = (st - sr) / st
    print("Power model regression:")
    print("a2:", a[0], "  B:", a[1])
    print("sy/x:", round(syx, 5))
    print("r2:", round(r2, 5))
    print("")

def saturationGrowthRate(x, y):
    n = len(x)
    invy = [0 for i in x]
    invx = [0 for i in x]
    for i in range(n):
        invy[i] = 1/y[i]
        invx[i] = 1/x[i]
    a = linearRegression(invx, invy)
    #Cambiar coeficientes a power model
    a[0] = 1 / a[0]
    a[1] = a[1]*a[0]
    sr = 0
    st = 0
    ym = 0
    for i in range(n):
        ym += y[i]
        sr += (y[i] - (a[0] * x[i] / (a[1] + x[i])))**2
    ym /= n
    for valor in y:
        st += (valor-ym)**2
    syx = (sr / (n-2))**0.5
    r2 = (st - sr) / st
    print("Saturation-growth-rate model regression:")
    print("a2:", a[0], "  B:", a[1])
    print("sy/x:", round(syx, 5))
    print("r2:", round(r2, 5))
    print("")

def nonLinear(x, y, a0, a1, es = 0.00001):
    n = len(x)
    a0old = 0
    a1old = 0
    ea0 = es + 1
    ea1 = es + 1
    while ea0 > es and ea1 > es :
        #a0(1-e^-a1x)
        z = [[1-math.exp(-a1*x[i]), a0 * x[i]*math.exp(-a1*x[i])] for i in range(n)]
        d = [[y[i] - (a0 * (1-math.exp(-a1*x[i])))] for i in range(n)]
        matr = multiplyMatrix(transpose(z), z)
        rhs = multiplyMatrix(transpose(z), d)
        sol1 = gaussianElimination([[
        matr[0][0], matr[0][1], rhs[0][0]], [
        matr[1][0], matr[1][1], rhs[1][0]]])
        a0old = a0
        a1old = a1
        a0 += sol1[0]
        a1 += sol1[1]
        ea0 = abs((a0-a0old)/a0)
        ea1 = abs((a1-a1old)/a1)
    sr = 0
    st = 0
    ym = 0
    for i in range(n):
        ym += y[i]
        sr += (y[i] - (a0 * (1-math.exp(-a1*x[i]))))**2
    ym /= n
    for valor in y:
        st += (valor-ym)**2
    syx = (sr / (n-2))**0.5
    r2 = (st - sr) / st
    print("Non linear regression model:")
    print("a0:", a0, " a1:", a1)
    print("sy/x:", round(syx, 5))
    print("r2:", round(r2, 5))
    print("")

x = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30]

y = [23,22,24,25,26,24,25,26,27,28,26,24,27,30,30,31,32,32,31,30,31,30,30,31,31,32,31,31,31,35]
print(len(y))

linearRegression(x, y)
polynomialRegression(x, y, 1)
polynomialRegression(x, y, 2)
exponentialModel(x, y)
powerModel(x, y)
saturationGrowthRate(x, y)
nonLinear(x, y, 30, 2, 0.00001)
