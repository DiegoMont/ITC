esHombre(bart).
esHombre(abraham).
esHombre(clancy).
esHombre(homero).

esPadre(homero,bart).
esPadre(homero,lisa).
esPadre(homero,maggie).
esPadre(abraham,homero).
esPadre(clancy,marge).

esMujer(mona).
esMujer(jackie).
esMujer(marge).
esMujer(lisa).
esMujer(maggie).

esMadre(mona,homero).
esMadre(jackie,marge).
esMadre(marge,bart).
esMadre(marge,lisa).
esMadre(marge,maggie).

% reglas
es_abuela(X,Y):-
esMadre(X,Z),esMadre(Z,Y);
esMadre(X,Z),esPadre(Z,Y).

es_abuelo(X,Y):-
esPadre(X,Z),esPadre(Z,Y);
esPadre(X,Z),esMadre(Z,Y).

es_bro(X,Y):-
esPadre(Z,X),esPadre(Z,Y);
esMadre(Z,X),esMadre(Z,Y).

es_hermano(X,Y):-
esHombre(X),
es_bro(X,Y).

es_hermana(X,Y):-
esMujer(X),
es_bro(x,Y).

es_grandchild(X,Y):-
esPadre(Y,Z),esPadre(Z,X);
esPadre(Y,Z),esMadre(Z,X);
esMadre(Y,Z),esPadre(Z,X);
esMadre(Y,Z),esMadre(Z,X).

es_nieto(X,Y):-
es_grandchild(X,Y),
esHombre(X).

es_nieta(X,Y):-
es_grandchild(X,Y),
esMujer(X).
