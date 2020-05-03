def enEspera(word, board, row, column):
    if row < 0 or column < 0 or row == len(board) or len(board[0]) == column:
        return False
    n = len(word)
    if word[0] == board[row][column]:
        if n == 1:
            return True
        else:
            return enEspera(word[1:], board, row-1, column-1) or enEspera(word[1:], board, row-1, column) or enEspera(word[1:], board, row-1, column+1) or enEspera(word[1:], board, row, column-1) or enEspera(word[1:], board, row, column+1) or enEspera(word[1:], board, row+1, column-1) or enEspera(word[1:], board, row+1, column) or enEspera(word[1:], board, row+1, column+1)
    else:
        return False

def isValid(word, board):
    for row in range(len(board)):
        for column in range(len(board[row])):
            if board[row][column] == word[0]:
                return enEspera(word, board, row, column)
    return False

def countWords(words, board):
    count = 0
    for word in words:
        if isValid(word, board):
            count += 1
    return count

####################
words = ["GEEKS", "FOR", "QUIZ", "GO", "KEEQ"]
board = [["G","I","Z"],
         ["U","E","K"],
         ["Q","S","E"]]
res = countWords(words, board)
print(res)
