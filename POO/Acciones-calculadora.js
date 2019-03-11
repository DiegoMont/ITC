let valorPantalla = 0;
let num1 = 0; let num2;
let memory;
let operacion = "ninguna";
let ultimoTecla = "tecla";//tecla operacion
let pantalla =document.getElementById("screen");

document.getElementById("medidasAngulares").addEventListener("click", function(){
  if (this.innerHTML==="DEG") {
    this.innerHTML = "RAD";
  } else {
    this.innerHTML = "DEG";
  }
});

document.getElementById("uno").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 1;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("dos").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 2;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("tres").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 3;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("cuatro").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 4;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("cinco").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 5;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("seis").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 6;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("siete").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 7;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("ocho").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 8;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("nueve").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 9;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("cero").addEventListener("click", function(){
  if(ultimoTecla === "operacion"){
    valorPantalla=0;
  }
  valorPantalla *= 10;
  valorPantalla += 0;
  pantalla.innerHTML = valorPantalla;
  ultimoTecla = "tecla";
});

document.getElementById("clean").addEventListener("click", function(){
  valorPantalla = 0;
  pantalla.innerHTML = valorPantalla;
});

document.getElementById("cleanEverything").addEventListener("click", function(){
  num1 = undefined;
  num2 = undefined;
  operacion = "ninguna";
  ultimoTecla = "tecla";
  valorPantalla = 0;
  pantalla.innerHTML = valorPantalla;
});

function realizarOperacion() {
  let resultado;
  switch (operacion) {
    case "sumar":
      resultado = num1 + num2;
      break;
    case "restar":
      resultado = num1 - num2;
      break;
    case "multiplicar":
      resultado = num1 * num2;
      break;
    case "dividir":
      resultado = num1 / (num2===0?1:num2);
      break;
    default: resultado = 0;
  }
  num1 = undefined;
  num2 = undefined;
  valorPantalla = resultado;
  pantalla.innerHTML = valorPantalla;
}

document.getElementById("sumar").addEventListener("click", function(){
  operacion="sumar";
  if (ultimoTecla==="tecla") {
    if (typeof num1 === "undefined") {
      num1 = valorPantalla;
    } else {
      num2 = valorPantalla;
      realizarOperacion();
      num1 = valorPantalla;
    }
  }
  ultimoTecla = "operacion";
});

document.getElementById("restar").addEventListener("click", function(){
  operacion="restar";
  if (ultimoTecla==="tecla") {
    if (typeof num1 === "undefined") {
      num1 = valorPantalla;
    } else {
      num2 = valorPantalla;
      realizarOperacion();
      num1 = valorPantalla;
    }
  }
  ultimoTecla = "operacion";
});

document.getElementById("multiplicar").addEventListener("click", function(){
  operacion="multiplicar";
  if (ultimoTecla==="tecla") {
    if (typeof num1 === "undefined") {
      num1 = valorPantalla;
    } else {
      num2 = valorPantalla;
      realizarOperacion();
      num1 = valorPantalla;
    }
  }
  ultimoTecla = "operacion";
});

document.getElementById("dividir").addEventListener("click", function(){
  operacion="dividir";
  if (ultimoTecla==="tecla") {
    if (typeof num1 === "undefined") {
      num1 = valorPantalla;
    } else {
      num2 = valorPantalla;
      realizarOperacion();
      num1 = valorPantalla;
    }
  }
  ultimoTecla = "operacion";
});

document.getElementById("resultado").addEventListener("click", function(){
  if (operacion !== "ninguna" && typeof num1 !== "undefined") {
    num2 = valorPantalla;
    realizarOperacion();
    operacion = "ninguna";
  }
});
