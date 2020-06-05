def eulerMethod(x0, y0, x, n = 100):
    print("Euler method differentiation")
    h = (x - x0) / n
    print("h=", h)
    yi = y0
    for i in range(n):
        xi = x0 + h*(i)
        yi1 = yi + fPrima(yi, xi) * h
        yi = yi1
    print("y(" + str(x) + ")=", yi, "\n")
    return yi

def heunsMethod(x0, y0, x, n = 100):
    print("Heun's method differentiation")
    h = (x - x0) / n
    print("h=", h)
    yi = y0
    for i in range(n):
        xi = x0 + h*(i)
        xi1 = x0 + h*(i+1)
        yi10 = yi + fPrima(yi, xi) * h
        yi1 = yi + (fPrima(yi, xi) + fPrima(yi10, xi1)) * h / 2
        yi = yi1
    print("y(" + str(x) + ")=", yi, "\n")
    return yi

def midpointMethod(x0, y0, x, n = 100):
    print("Midpoint method differentiation")
    h = (x - x0) / n
    print("h=", h)
    yi = y0
    for i in range(n):
        xi = x0 + h*(i)
        xi1 = xi + h/2
        yi10 = yi + fPrima(yi, xi) * h/2
        yi1 = yi + fPrima(yi10, xi1) * h
        yi = yi1
    print("y(" + str(x) + ")=", yi, "\n")
    return yi

def ralstonsMethod(x0, y0, x, n = 100):
    print("Ralston's method differentiation")
    h = (x - x0) / n
    print("h=", h)
    yi = y0
    for i in range(n):
        xi = x0 + h*(i)
        xi1 = xi + 3 * h/4
        yi10 = yi + 3 * fPrima(yi, xi) * h/4
        yi1 = yi + (fPrima(yi, xi) + 2 * fPrima(yi10, xi1)) * h/3
        yi = yi1
    print("y(" + str(x) + ")=", yi, "\n")
    return yi
