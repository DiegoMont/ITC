"""
Diego Montaño Martinez
A01651308
"""
#The frog and the stream
def streamAndFrog(rocks):
    if rocks < 0:
        return 0
    if rocks == 0:
        return 1
    else:
        return streamAndFrog(rocks-1) + streamAndFrog(rocks-2)
"""
c(0) = 1
c(1) = 1
c(2) = c(1) + c(0) = 1 + 1 = 2
c(3) = c(2) + c(1) = 2 + 1 = 3
c(4) = c(3) + c(2) = 3 + 2 = 5
Time complexity: c(n) belongs to O(2^n)
Space complexity: n
"""

#Filling the gaps
def fill(canvas, x, y, newColor):
    currentColor = canvas[y][x]
    canvas[y][x] = newColor
    if x > 0 and canvas[y][x-1] == currentColor:
        fill(canvas, x-1, y, newColor)
    if y > 0 and canvas[y-1][x] == currentColor:
        fill(canvas, x, y-1, newColor)
    if x < len(canvas[0])-1 and canvas[y][x+1] == currentColor:
        fill(canvas, x+1, y, newColor)
    if y < len(canvas)-1 and canvas[y+1][x] == currentColor:
        fill(canvas, x, y+1, newColor)

canvas = [[
1, 1, 1, 1, 1], [
1, 0, 0, 0, 1], [
0, 1, 0, 0, 1], [
0, 0, 3, 0, 1], [
0, 0, 0, 1, 1]]

fill(canvas, 3, 2, 3)
for i in canvas:
    linea = ""
    for j in i:
        linea += str(j) + " "
    #print(linea)

"""
c(1, 1) = 1
c(m, 1) = m
c(1, n) = n
c(m, n) = mn
Time complexity: c(m, n) that belong to O(m*n)
Space complexity: m * n
"""

#Boolean evaluation
def evaluar(numVariables, operadores, resultado):
    #print(numVariables, operadores, resultado)
    if numVariables == 1:
        if resultado:
            return 1
        else:
            return 0
    if operadores[0] == "|":
        return evaluar(numVariables-1, operadores[1:len(operadores)], resultado or True) + evaluar(numVariables-1, operadores[1:len(operadores)], resultado or False)
    else:
        return evaluar(numVariables-1, operadores[1:len(operadores)], resultado and True) + evaluar(numVariables-1, operadores[1:len(operadores)], resultado and False)

def booleanEvaluation(expresion):
    numeroDeVariables = 1
    operadores = ""
    index = 1
    while index < len(expresion):
        caracter = expresion[index]
        if caracter == "&" or caracter == "|":
            operadores += caracter
            numeroDeVariables += 1
        index += 1
    return evaluar(numeroDeVariables, operadores, True) + evaluar(numeroDeVariables, operadores, False)

print(booleanEvaluation("x"))
print(booleanEvaluation("x1&x2"))
print(booleanEvaluation("x1|x2"))
print(booleanEvaluation("x1&x2|x3"))
print(booleanEvaluation("x1|x2|x3"))
print(booleanEvaluation("x1|x2&x3"))

"""
Time complexity: c(n) that belongs to Big O(2^n)
Space complexity: n
"""
