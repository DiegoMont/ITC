# Programa en C
# int iterations = 10;
# i = 0;
# int ans = 17;
#for(i = 0; i < 10; i++)
#   val += i;

.text
.globl main
main:
    li $t0, 10
    li $t1, 0
    li $t2, 17
loop:
    beq $t1, $t0, end
    add $t2, $t2, $t1
    addi $t1, $t1, 1
    j loop
end:
    li $v0, 10
    syscall
