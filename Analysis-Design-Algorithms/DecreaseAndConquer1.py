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
Space complexity: 1
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
0, 0, 0, 0, 1], [
0, 0, 0, 1, 1]]

fill(canvas, 3, 2, 3)
for i in canvas:
    linea = ""
    for j in i:
        linea += str(j) + " "
    print(linea)

"""
c(1, 1) = 1
c(m, 1) = m
c(1, n) = n
c(m, n) = mn
Time complexity: c(m, n) that belong to O(m, n)
Space complexity:
"""

#Boolean evaluation
cuantasRegresanTrue = 0
def booleanEvaluation(expression):
    operador = ""
    index = 0
    global cuantasRegresanTrue
    for i in range(len(expression)):
        if expression[i] == "|" or expression[i] == "&":
            operador = expression[i]
            index = i
    if operador == "":
        cuantasRegresanTrue += 1
        return True
    nuevaExpresion = expression[index:len(expression)]
    if operador == "|":
        if (True or booleanEvaluation(nuevaExpresion)):
            cuantasRegresanTrue += 1
        if (False or booleanEvaluation(nuevaExpresion)):
            cuantasRegresanTrue += 1
    else:
        if True and booleanEvaluation(nuevaExpresion):
            cuantasRegresanTrue += 1
        if False and booleanEvaluation(nuevaExpresion):
            cuantasRegresanTrue += 1
    return True


print(booleanEvaluation("x"))
print(cuantasRegresanTrue)
cuantasRegresanTrue = 0
booleanEvaluation("x&y")
print(cuantasRegresanTrue)
cuantasRegresanTrue = 0
print(booleanEvaluation("x&e|c"))
print(cuantasRegresanTrue)
