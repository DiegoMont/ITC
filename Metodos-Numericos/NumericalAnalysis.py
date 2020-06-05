def Bisection(xl, xu, es = 0.001):
    print("Bisection: " + str(BisectionRecursivo(xl, xu, es, 0, 0)) + "\n")

def BisectionRecursivo(xl, xu, es, contador, xrOld):
  xr = (xl + xu) / 2
  multiplicacion = f(xl) * f(xr)
  if multiplicacion == 0 or contador == 1000 or (contador > 0 and calculateEa(xrOld, xr) < es):
      print(contador);
      return xr;
  elif multiplicacion < 0:
      return BisectionRecursivo(xl, xr, es, contador+1, xr)
  else:
      return BisectionRecursivo(xr, xu, es, contador+1, xr)

def calculateEa(ErOld, ErNew):
    return abs(ErNew - ErOld) * 100 / ErNew

def NewtonRaphson(xi, es = 0.001):
    xi1 = 0
    for x in range(1000):
        xi1 = xi - (f(xi)/fPrima(xi))
        #print("Iteracion " + str(x) + "  xi+1= " + str(xi1))
        if(calculateEa(xi, xi1) < es):
            print("Iterations: " + str(x))
            break
        xi = xi1
    print("Newton-Raphson: " + str(xi1) + "\n")

def secant(xi1, xi, es = 0.001):
    for x in range(1000):
        temp = xi
        xi = xi - (f(xi)*(xi1 - xi))/(f(xi1) - f(xi))
        xi1 = temp
        if(calculateEa(xi1, xi) < es):
            print("Iterations: " + str(x))
            break
    print("Secant method: "+str(xi) + "\n")

def f(x):
    return x
