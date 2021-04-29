def cuadradoLista(x):
  if x == 1:
    return [1]
  else:
    lista = cuadradoLista(x-1)
    lista.append(x * x)
    return lista

print(cuadradoLista(5))

f = lambda z: z * 11

print(f(11))

L = lambda x: x + 2

print(L(3))

f = lambda a, b: a * b

print(f(3, 4))

compareTo = lambda x, y: x > y

lista = [3, 7, 2, 5, 8]
lista.sort(lista, compareTo)
print(listaOrdenada)
