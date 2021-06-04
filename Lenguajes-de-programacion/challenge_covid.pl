es_covid(nausea).
es_covid(diarrea).
es_covid(dolor_pecho).
es_covid(dificultad_respirar).
es_covid(perdida_olfato).
es_covid(decoloracion_dedos).
es_covid(tos).
es_covid(fiebre).
es_covid(irritacion_garganta).
es_covid(cuerpo_cortado).
es_covid(dolor_de_cabeza).
es_covid(fatiga).

es_influenza(tos).
es_influenza(fiebre).
es_influenza(irritacion_garganta).
es_influenza(congestion_nasal).
es_influenza(cuerpo_cortado).
es_influenza(dolor_de_cabeza).
es_influenza(fatiga).

es_resfriado(tos).
es_resfriado(fiebre).
es_resfriado(irritacion_garganta).
es_resfriado(congestion_nasal).
es_resfriado(cuerpo_cortado).
es_resfriado(dolor_de_cabeza).

dolor_pecho(covid).

dificultad_respirar(covid).

perdida_olfato(covid).

decoloracion_dedos(covid).

fatiga(covid).
fatiga(influenza).

congestion_nasal(influenza).
congestion_nasal(resfriado).

tos(covid).
tos(influenza).
tos(resfriado).

fiebre(covid).
fiebre(influenza).
fiebre(resfriado).

irritacion_garganta(covid).
irritacion_garganta(influenza).
irritacion_garganta(resfriado).

cuerpo_cortado(covid).
cuerpo_cortado(influenza).
cuerpo_cortado(resfriado).

dolor_de_cabeza(influenza).
dolor_de_cabeza(resfriado).
dolor_de_cabeza(covid).

% Reglas

% COVID 19
covid([], COVID):-COVID is 0.
covid([H|T], COVID):-
    covid(T, Aux),
    (es_covid(H)->  
      COVID is Aux + 1;
      COVID is Aux).

% Influenza
influenza([], INFLUENZA):-INFLUENZA is 0.
influenza([H|T], INFLUENZA):-
    influenza(T, Aux),
    (es_influenza(H)->
        INFLUENZA is Aux + 1;
        INFLUENZA is Aux).

% Resfriado
resfriado([], RESFRIADO):-RESFRIADO is 0.
resfriado([H|T], RESFRIADO):-
    resfriado(T, Aux),
    (es_resfriado(H)->
        RESFRIADO is Aux + 1;
        RESFRIADO is Aux).

add_sintoma(Sintomas):-read(S),add_sintoma(S, Sintomas).
add_sintoma(listo, []):-!.
add_sintoma(S, [S | Sintomas]):-read(NS), add_sintoma(NS, Sintomas).

imprimir_sintomas([H|T]):-write(H), nl, imprimir_sintomas(T).
imprimir_sintomas([]):-!.

diagnosticar():-
  write("Ingrese su nombre:"),nl,
  write("De la lista de síntomas que mostraremos a continuación, escriba los que apliquen a su caso."), nl,
  write("Cuando haya escrito todos sus sintomas escriba: listo"), nl,
  imprimir_sintomas([dolor_pecho, dificultad_respirar, perdida_olfato, decoloracion_dedos, fatiga, congestion_nasal, tos, fiebre, irritacion_garganta, cuerpo_cortado, dolor_de_cabeza]),
  add_sintoma(Sintomas),
  covid(Sintomas, COVID),
  influenza(Sintomas, INFLUENZA),
  resfriado(Sintomas, RESFRIADO),
  write(Sintomas),nl,
  write("Síntomas COVID "), write(COVID), nl,
  write("Síntomas Influenza "), write(INFLUENZA), nl,
  write("Síntomas resfriado "), write(RESFRIADO), nl,
  (COVID>INFLUENZA, COVID>RESFRIADO)-> write("Diagnosticamos que tiene COVID-19");
  (INFLUENZA>COVID, INFLUENZA>RESFRIADO)->write("Diagnosticamos que tiene Influenza");
  (RESFRIADO>COVID, RESFRIADO>INFLUENZA)->write("Diagnosticamos que tiene resfriado");
  (COVID =:= RESFRIADO, RESFRIADO=:=INFLUENZA)->write("No es posible hacer un diagnóstico");
  write("Usted no cuenta con ningun síntoma de estas enfermedades").