def sumatoriaRecursiva(X):
  if X == 0:
    return 0
  else:
    return X + sumatoriaRecursiva(X-1)

sumatoriaRecursiva(0)
sumatoriaRecursiva(3)
sumatoriaRecursiva(5)