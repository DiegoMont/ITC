universo(dc).
universo(marvel).
superhero_de(superman, dc).
superhero_de(flash, dc).
superhero_de(wonderWoman, dc).
superhero_de(batman, dc).
superhero_de(greenLatern, dc).
superhero_de(guyGartner, dc).
superhero_de(capMarvel, dc).
superhero_de(capAmerica, marvel).
superhero_de(ironMan, marvel).
superhero_de(thor, marvel).
superhero_de(hulk, marvel).
superhero_de(capMarvel, marvel).

cantidad_personajes(Universo, Contador):-
    findall(Personaje, superhero_de(Personaje, Universo), Lista),
    write(Lista),
    length(Lista, Resultado),
    Contador is Resultado.

lista_super(Lista):- read(Producto), lista_super(Producto, Lista).
lista_super(finito, []):- !.
lista_super(Producto, [Producto | Carrito]):-read(NuevoProducto),lista_super(NuevoProducto, Carrito).

super:- 
    write('Lista del super:'),
    nl,
    write('Escribe finito para terminar'),
    nl,
    lista_super(Lista),
    write('La lista es: '),
    write(Lista).
