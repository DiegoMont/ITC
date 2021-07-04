hombre(eduardo).
hombre(francisco).
hombre(luis).
hombre(mario).
mujer(alicia).
mujer(veronica).
mujer(victoria).
mujer(beatriz).
padres(eduardo,francisco,victoria).
padres(alicia,francisco,victoria).
padres(luis,eduardo,veronica).
padres(beatriz,alicia,mario).
esposos(eduardo,veronica).
esposos(mario,alicia).
esposos(francisco,victoria).

% Reglas
siblings(Hermano,Alguien):-
  padres(Hermano,Padre,Madre),
  padres(Alguien,Padre,Madre).

hermano(Hermano,Alguien):-
  hombre(Hermano),
  siblings(Hermano,Alguien).

hermana(Hermana,Alguien):-
  mujer(Hermana),
  siblings(Hermana,Alguien).

hije(Hijo,Padre):-
  padres(Hijo,Padre,_);
  padres(Hijo,_,Padre).

hijo(H,Padre):-
  hombre(H),hije(H,Padre).
  
hija(H,Padre):-
  mujer(H),hije(H,Padre).

% Consultas

% ¿Eduardo y Alicia son hermanos?
hermano(eduardo,alicia).

% ¿Quienes son los padres de Beatriz?
hija(beatriz,Padre).

% ¿Eduardo es hijo de Mario?
hijo(eduardo,mario).

% ¿Luis es hijo de Verónica?
hijo(luis,veronica).

% ¿De quién es hija Beatriz?
hija(beatriz,Padre).