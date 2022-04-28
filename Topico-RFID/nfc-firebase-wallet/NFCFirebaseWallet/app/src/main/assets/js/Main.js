const main = function(){
  console.log("Iniciando aplicación");

  const formRegistro = document.querySelector("#form-registro-cliente");
  formRegistro.addEventListener('submit', function(e) {
    e.preventDefault();
    const nombre = formRegistro['nombre-cliente-nuevo'].value;
    const saldoInicial = formRegistro['saldo-inicial'].value;
    const userID = Android.writeIDToTag(nombre);
    createNewAccount(userID, nombre, saldoInicial);
    console.log(userID);
    Android.loadModal();
  })
}

main();