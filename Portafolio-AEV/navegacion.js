var arregloContenidos = document.getElementsByClassName("contenido");

mostrarContenido(0);

function mostrarContenido(numBoton){
  for (var i = 0; i < arregloContenidos.length; i++) {
    arregloContenidos[i].style.display = "none";
  }
  arregloContenidos[numBoton].style.display = "block";
}
