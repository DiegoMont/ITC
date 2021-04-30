/*
Ejercicio 01:
Escribe las siguientes funciones como funciones puras y función de primera clase:
/*
1. Escriba una función llamada calculaEdadPerro que:
toma 1 argumento: la edad de su cachorro.
calcula la edad de su perro según la tasa de conversión de 1 año humano a 7 años de perro.
muestra el resultado en la pantalla de la siguiente manera: "¡Tu perrito tiene NN años en años de perro!"
Llame a la función tres veces con diferentes conjuntos de valores. 
*/
const calculaEdadPerro = edad => `¡Tu perrito tiene ${edad * 7} años en años de perro!`;

console.log(calculaEdadPerro(1));
console.log(calculaEdadPerro(3));
console.log(calculaEdadPerro(10));
/*

2.¡Hace calor afuera! 
2.1 Cree una función llamada celsius2Fahrenheit:
Almacena una temperatura en grados centígrados en una variable.
Conviértalo a Fahrenheit y la salida "NN ° C es NN ° F".

*/

function celsius2Fahrenheit()
{
  const gradosC = 10;
  const gradosF = (gradosC*1.8) + 32
  return `${gradosC} ° C es ${gradosF} ° F`;
}

celsius2Fahrenheit()

/*2.2 Cree una función llamada fahrenheitToCelsius:
Ahora guarde una temperatura en grados Fahrenheit en una variable.
Conviértalo a grados Celsius y la salida "NN ° F es NN ° C".
*/

function fahrenheitToCelsius()
{
  gradosF = 10;
  gradosC = (gradosF-32) / 1.8
  console.log(gradosF+ " ° F es "+gradosC +"° C")
}
fahrenheitToCelsius()

// Ejercicio 02:

let starwars = [
  {
    id: 5,
    nombre: "Luke Skywalker",
    calificacionVuelo: 98,
    calificacionDisparo: 56,
    usaFuerza: true,
  },
  {
    id: 82,
    nombre: "Sabine Wren",
    calificacionVuelo: 73,
    calificacionDisparo: 99,
    usaFuerza: false,
  },
  {
    id: 22,
    nombre: "Zeb Orellios",
    calificacionVuelo: 20,
    calificacionDisparo: 59,
    usaFuerza: false,
  },
  {
    id: 15,
    nombre: "Ezra Bridger",
    calificacionVuelo: 43,
    calificacionDisparo: 67,
    usaFuerza: true,
  },
  {
    id: 11,
    nombre: "Caleb Dume",
    calificacionVuelo: 71,
    calificacionDisparo: 85,
    usaFuerza: true,
  },
    {
    id: 66,
    nombre: "Darth Vader",
    calificacionVuelo: 10000,
    calificacionDisparo: 500,
    usaFuerza: false,
  },
];

/*
1. Obtén a los personajes que no pueden usar la fuerza, es decir la salida es: jedi (un arreglo).
*/
let jedis = starwars.filter(jedi => jedi.usaFuerza);

console.log("Jedis", jedis);

/*
2. Usando a la colección de jedi, del punto 1, obtén un arreglo con el promedio de sus dos calificaciones, calificacionesJedi (un arreglo).*/
let calificacionesJedi = jedis.map(jedi => (jedi.calificacionVuelo + jedi.calificacionDisparo) / 2);

console.log("calificacionesJedi", calificacionesJedi);

/*
3. Totaliza las calificacionesJedi*/
let sumaCalificaciones = calificacionesJedi.reduce((acumulado, promedio) => acumulado + promedio, 0);

console.log("Total calificaciones", sumaCalificaciones)

/*
4. De los siths, obtén cuál es el sith con mejor calificación de disparo. */
let siths = starwars.filter(jedi => !jedi.usaFuerza);

let sithMejorDisparo = siths.reduce(function(acumulado, actual) {
  return (acumulado.calificacionDisparo || 0) > actual.calificacionDisparo ? acumulado: actual;
}, {});

console.log("mejor calificacion disparo", sithMejorDisparo);

/*
5. De los siths, obtén el promedio de sus calificaciones de vuelo.  
*/
let promedioVueloSiths = siths.reduce((acumulado, sith) => acumulado + sith.calificacionVuelo, 0) / siths.length;

console.log("promedio de vuelo", promedioVueloSiths)
