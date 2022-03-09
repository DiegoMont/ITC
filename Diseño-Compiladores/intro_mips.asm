# asignación
# int variable = valor;
.text
.globl main
main:
    li $t0, valor;
    li $v0, 10
    syscall


# declaración
# int variable;
.text
.globl main
main:
    li $t0, 0;
    li $v0, 10
    syscall


# if sin else
# if(true) {
#     subexp;
# }
.text
.globl main
main:
    beqz $v0, end
then:
    # cuerpo del if
end:
    li $v0, 10
    syscall


# if con else
# if(true) {
#     then;
# } else {
#     else;
# }
.text
.globl main
main:
    beqz $v0, else
then:
    # cuerpo del if
    b end
else:
    # cuerpo del else
end:
    li $v0, 10
    syscall


# while
# while(true){
#     instrucciones;
# }
.text
.globl main
main:
    beqz $v0, end
while_block:
    # cuerpo del while
    b main
end:
    li $v0, 10
    syscall


# do while
# do {
#     instrucciones;
# } while(true)
.text
.globl main
main:
    # instrucciones
condicion:
    bgtz $v0, main
end:
    li $v0, 10
    syscall

# aritmética
# int suma = a + b;
# int resta = a - b;
# int multiplicacion = a * b;
# int division = a / b;
.text
.globl main
main:
    add $t0, $v0, $v1
    sub $t1, $v0, $v1
multiplicacion:
    mult $v0, $v1
multiplicacion:
    div $v0, $v1
    li $v0, 10
    syscall

# comparacion
sgt $t1, $v0, $v1
