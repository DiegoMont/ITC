const carroNuevo = "<div class='vehiculo'><div class='info-general'><h5><span>Matr&iacute;cula: </span><span class='placa'>56</span></h5><div class='engomado'></div></div><p><span>Propietario: </span>Juanito P&eacute;rez</p><p class='btn-detalles btn'>> Detalles sobre infracciones</p><div class='informacion-multas ocultar'><div class='multa'><h6>Multa</h5><p><span>Ubicaci&oacute;n: </span> Tec CCM</p><p><span>Horario: </span> 15:39</p><p><span>Cargo: </span> $1439.00</p></div></div><h6>Total: $1439.00</h6></div>";

function pintarEngomado() {
  let placas = document.getElementsByClassName("placa");
  let engomados = document.getElementsByClassName("engomado");
  let btnDetalles = document.getElementsByClassName("btn-detalles");
  let infoMultas = document.getElementsByClassName("informacion-multas");
  for (let i = 0; i < placas.length; i++) {
    const ultimaLetra = placas[i].innerText.charAt(placas[i].innerText.length-1);
    if (ultimaLetra === "5" || ultimaLetra === "6") {
      engomados[i].classList.add("background-amarillo");
    } else if (ultimaLetra === "7" || ultimaLetra === "8") {
      engomados[i].classList.add("background-rosa");
    } else if (ultimaLetra === "3" || ultimaLetra === "4") {
      engomados[i].classList.add("background-rojo");
    } else if (ultimaLetra === "1" || ultimaLetra === "2") {
      engomados[i].classList.add("background-verde");
    } else if (ultimaLetra === "0" || ultimaLetra == "9") {
      engomados[i].classList.add("background-azul");
    }
    btnDetalles[i].addEventListener("click", function() {
      infoMultas[i].classList.toggle("ocultar");
      let texto = btnDetalles[i].innerText;
      if (texto.charAt(0) === ">") {
        btnDetalles[i].innerText = "^ Detalles sobre infracciones";
      } else {
        btnDetalles[i].innerText = "> Detalles sobre infracciones";
      }
    });
  }
}

pintarEngomado();
