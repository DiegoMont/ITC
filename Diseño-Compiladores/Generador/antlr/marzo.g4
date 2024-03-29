grammar marzo;

program : statement+ ;

statement:
    Variable '=' expression #asignacion
    ;

expression: 
    expression '+' expression #suma
    | expression '-' expression #resta
    | expression '*' expression #multiplicacion
    | expression '/' expression #division
    | expression '<' expression #menor_que
    | expression '>' expression #mayor_que
    | Numero                  #primaria
    ;


// A continuación los tokens (comienzan con mayúscula)
Numero : [0-9]+;
Variable : [a-zA-Z]+;
WS : [ \t\n\r]+ -> skip ;
