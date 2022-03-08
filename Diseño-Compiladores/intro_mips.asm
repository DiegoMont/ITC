# asignación

# declaración

# if sin else
sgt $t1, $v0, $v1
bgtz $t1, -2
instrucciones

# if con else
sgt $t1, $v0, $v1
bgtz $t1, -2
instrucciones
j
else

# while
sgt $t1, $v0, $v1
beqz $t1, 3
instrucciones
j -6

# do while
instrucciones
sgt $t1, $v0, $v1
bgtz $t1, -2

# aritmética
addu $t1, $v0, $v1

# comparacion
sgt $t1, $v0, $v1
