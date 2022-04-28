// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyACg75lLrPj4w5H33ROQVpsRCLYGGaLnUs",
  authDomain: "nfc-firebase-wallet.firebaseapp.com",
  projectId: "nfc-firebase-wallet",
  storageBucket: "nfc-firebase-wallet.appspot.com",
  messagingSenderId: "942388963076",
  appId: "1:942388963076:web:1c57a07fb0b8b55a47d251"
};

firebase.initializeApp(firebaseConfig);

const db = firebase.firestore();
db.settings({ timestampsInSnapshots: true });

const createNewAccount = function(userID, nombre, saldoInicial){
  const tarjeta = {};
    tarjeta.nombre = nombre;
    tarjeta.saldo = saldoInicial;
    tarjeta.transacciones = [];
    tarjeta.transacciones.push(createTransaction('Cuenta creada', saldoInicial));
    db.collection('Tarjetas').doc(userID.toString()).set(tarjeta).then(function(){
      console.log("El usuario ha sido guardado exitosamente");
    });
};

const createTransaction = function(operacion, cantidad) {
  const transaction = {};
  transaction.operacion = operacion;
  transaction.monto = cantidad;
  transaction.date = Date.now();
  return transaction;
}

const getTarjeta = async function(userID){
  const snapshot = await db.collection('Tarjetas').doc(userID.toString()).get();
  return snapshot.data();
};

const registerSale = function(userID, total){
  let saldoNuevo = Number(tarjeta.saldo);
  saldoNuevo -= Number(total);
  tarjeta.saldo = saldoNuevo;
  tarjeta.transacciones.push(createTransaction('Compra', total));
  db.collection('Tarjetas').doc(userID.toString()).set(tarjeta);
};

const updateBalance = function(userID, balanceToAdd){
  let saldoNuevo = Number(tarjeta.saldo);
  saldoNuevo += Number(balanceToAdd);
  tarjeta.saldo = saldoNuevo;
  tarjeta.transacciones.push(createTransaction('Depósito', balanceToAdd));
  db.collection('Tarjetas').doc(userID.toString()).set(tarjeta);
};