grammar marzo;

program : expression+ ;

expression: 
    expression '+' expression #suma
    | Numero                  #primaria
    ;


// A continuación los tokens (comienzan con mayúscula)
Numero : [0-9]+;
WS : [ \t\n\r]+ -> skip ;
