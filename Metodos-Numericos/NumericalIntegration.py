import math

def numericalIntegration(a, b, n = 500):
    h = (b - a) / n
    B = h / 2
    I = 0
    for i in range(n):
        #print(a+B, f(a+B))
        I += h * f(a+B)
        B += h
    print("Numerical integration:\nI:", I)
    return I

def trapezoidalRule(a, b, n = 500):
    h = (b - a) / n
    x = [0 for i in range(n+1)]
    for i in range(n+1):
        x[i] = a + h*i
    sum = 0
    for i in range(1, n):
        sum += f(x[i])
    I = h * (f(x[0]) + 2 * sum + f(x[n])) / 2
    print("Trapezoidal Rule:\nI:", I)
    return I

def simpsonsRule(a, b, n = 500):
    h = (b - a) / n
    x = [0 for i in range(n+1)]
    sumaPar = 0
    sumaImpar = 0
    for i in range(n+1):
        x[i] = a + h*i
    for i in range(1, n):
        if i % 2 == 0:
            sumaPar += f(x[i])
        else:
            sumaImpar += f(x[i])
    I = (b - a) * (f(x[0]) + 4*sumaImpar + 2*sumaPar + f(x[n])) / 3 / n
    print("Simpson's Rule:\nI:", I)
    return I

def bilinearIntegration(x0, xn, y0, yn, h = 0.1):
    nx = int((xn - x0) / h)
    ny = int((yn - y0) / h)
    print(nx, ny)
    suma = 0
    for i in range(nx):
        for j in range(ny):
            suma += f(x0 + i*h, y0 + j*h) + f(x0 + h*i, y0 + h*(j+1)) + f(x0 + h*(i+1), y0 + h*j) + f(x0 + h*(i+1), y0 + h*(j+1))
    I = h**2 / 4 * suma
    print("Bilinear Integration:\nI:", I, "\n")
    real = 4.85271
    error = (real-I) / real *100
    print("Error:", error)
    return I

def trapezoidArea(x, y):
    I = 0
    n = len(y)
    for i in range(n-1):
        area = abs((x[i+1] - x[i]) * (y[i+1] + y[i]) / 2)
        I += area
    print("The value of the integral is:", I);
    return I
