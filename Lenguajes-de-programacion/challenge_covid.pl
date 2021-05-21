es_covid(nausea).
es_covid(diarrea).
es_covid(dolorPecho).
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

nausea(covid).

diarrea(covid).

dolorPecho(covid).

dificultad_respirar(covid).

perdida_olfato(covid).

decoloracion_dedos(covid).

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

fatiga(covid).
fatiga(influenza).

congestion_nasal(influenza).
congestion_nasal(resfriado).

dolor_de_cabeza(influenza).
dolor_de_cabeza(resfriado).
dolor_de_cabeza(covid).

% Reglas

% COVID 19
covid([], R):-write(R).
covid([H|T], R):-
    (es_covid(H)->
        Aux is R + 1;
        Aux is R),
    covid(T, Aux).

% Influenza
influenza([], R):-write(R).
influenza([H|T], R):-
    (es_influenza(H)->
        Aux is R + 1;
        Aux is R),
    influenza(T, Aux).

% Resfriado
resfriado([], R):-write(R).
resfriado([H|T], R):-
    (es_resfriado(H)->
        Aux is R + 1;
        Aux is R),
    resfriado(T, Aux).
