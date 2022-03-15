grammar marzo;

program : expression+ ;

expression: 
    expression '+' expression # suma
    | expression '-' expression # resta
    | expression '*' expression # multiplicación
    | expression '/' expression # división
    | expression '<' expression # menor que
    | expression '>' expression # mayor que
    | Numero                  #primaria
    ;


// A continuación los tokens (comienzan con mayúscula)
Numero : [0-9]+;
WS : [ \t\n\r]+ -> skip ;
