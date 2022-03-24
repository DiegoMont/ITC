const sqlite3 = require('sqlite3').verbose();
const db = new sqlite3.Database('./bicicletas.dbf', (error) => {
    if(error)
        return console.error(error.message);
    console.log('Database connected successfully');
});

let Bicicleta = function(id, color, modelo, ubicacion){
    this.id = id
    this.color = color
    this.modelo = modelo
    this.ubicacion = ubicacion
}

db.serialize(function() {
    db.run("CREATE TABLE IF NOT EXISTS Bicicleta(id INTEGER NOT NULL, color TEXT NOT NULL, modelo TEXT NOT NULL, longitud REAL NOT NULL, latitud REAL NOT NULL)");
});

Bicicleta.prototype.toString = function(){
    return `Id: ${this.id}, color: ${this.color}`
}

Bicicleta.add = function(aBici){
    db.serialize(function() {
        db.run(`INSERT INTO Bicicleta VALUES(${aBici.id}, '${aBici.color}', '${aBici.modelo}', ${aBici.ubicacion[1]}, ${aBici.ubicacion[0]})`);
        db.get(`SELECT * FROM Bicicleta WHERE id = ${aBici.id}`, function(err, row) {
            if(err)
                throw new Error(`Falló el insert`);
            console.log(row);
        });
    });
}

//Añadir un par de bicis:
let b1 = new Bicicleta(1, 'rojo', 'bmx', [19.284770943610578, -99.13729060406136])
let b2 = new Bicicleta(2, 'blanca', 'Benotto', [19.286055116801744, -99.1369912899661])

Bicicleta.add(b1)
Bicicleta.add(b2)

//Eliminar
Bicicleta.findById = function(aBiciId){
    let aBici;
    db.serialize(function name() {
        db.get(`SELECT * FROM Bicicleta WHERE id = ${aBiciId}`, function(err, row) {
            if(err)
                throw new Error(`No existe una bici con el id: ${aBiciId}`);
            aBici = row;
        });
    });
    console.log(aBici);
    return aBici;
}

Bicicleta.removeById = function(aBiciId){
    db.serialize(function() {
        db.run(`DELETE FROM Bicicleta WHERE id = ${aBiciId}`);
    });
}

module.exports = Bicicleta