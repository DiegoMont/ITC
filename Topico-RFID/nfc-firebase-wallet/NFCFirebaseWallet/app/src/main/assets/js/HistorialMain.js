const tiendaSection = document.querySelector('#tienda');
const saldoSection = document.querySelector('#saldo');
const historialSection = document.querySelector('#historial');

let currentUserID = 0;
let tarjeta;

const hideSections = function() {
  tiendaSection.classList.add("ocultar");
  saldoSection.classList.add("ocultar");
  historialSection.classList.add("ocultar");
}

const renderHistory = function(){
  const container = document.querySelector('#historial .table-content');
  container.innerHTML = "";
  for (const transaccion of tarjeta.transacciones) {
    const date = new Date(transaccion.date);
    container.innerHTML += `<p>${transaccion.operacion}</p><p>$${transaccion.monto}</p><p>${date.toLocaleDateString()}</p>`;
  }
};

const updateNameAndBalance = function(){
    document.querySelector('#nombre').innerText = tarjeta.nombre;
    document.querySelector('#saldo-actual').innerHTML = `<span>Saldo actual:</span> $${tarjeta.saldo}`;
};

const addProduct = function(id, productName, productPrice){
  const storeDiv = document.querySelector('#form-tienda .table-content');
  let htmlProduct = `<input type="checkbox" name="producto" id="${id}" value="${productPrice}">
  <label for="${id}">${productName}</label>
  <p>$${productPrice}</p>`;
  storeDiv.innerHTML += htmlProduct;
};

const main = function() {
  hideSections();
  tiendaSection.classList.remove("ocultar");

  // Event Listeners del nav
  document.querySelector('#btn-tienda').addEventListener('click', function() {
    hideSections();
    tiendaSection.classList.remove("ocultar");
  });

  document.querySelector('#btn-saldo').addEventListener('click', function() {
    hideSections();
    saldoSection.classList.remove("ocultar");
  });

  document.querySelector('#btn-historial').addEventListener('click', function() {
    hideSections();
    historialSection.classList.remove("ocultar");
  });

  const updateBalanceForm = document.querySelector('#actualizar-saldo');
  updateBalanceForm.onsubmit = function(e){
    e.preventDefault();
    let addBalance = updateBalanceForm['input-saldo'].value;
    const error = document.querySelector('#error-saldo');
    if(addBalance == '')
      error.innerText = 'El saldo está vacío';
    if(addBalance <= 0){
      error.innerText = 'El deposito es demasiado pequeño';
      return;
    }
    
    updateBalance(currentUserID, addBalance);
    updateBalanceForm.reset();
  };

  const storeForm = document.querySelector('#form-tienda');
  storeForm.onsubmit = function(e){
    e.preventDefault();
    let total = 0;
    for (const input of storeForm['producto']) {
      if (input.checked) {
        total += Number(input.value);
      }
    }
    if(total > tarjeta.saldo)
      return;
    storeForm.reset();
    registerSale(currentUserID, total);
  };

  addProduct(1, 'Beatles for sale', 150);
  addProduct(2, 'Abbey Road (Deluxe Edition)', 300);
  addProduct(3 ,'Let it be', 150);
  addProduct(4, 'Yellow Submarine', 100);
  addProduct(5, 'White album', 200);

  //currentUserID = 675925504;
  currentUserID = Android.getUserID();
  getTarjeta(currentUserID).then(function(tarjetaDB){
    tarjeta = tarjetaDB;
    updateNameAndBalance();
    renderHistory();
  });

  db.collection('Tarjetas').doc(currentUserID.toString()).onSnapshot(function(snapshot){
    tarjeta = snapshot.data();
    updateNameAndBalance();
    renderHistory();
  });
}

main();