const assert = require('assert');
const Bicicleta = require('../models/bicicleta');
const Usuario = require('../models/usuario');
const Reserva = require('../models/reserva');

describe('Testing usuarios', function(){
    describe('Un usuario reserva una bici', bikeReservationTest);
});

function bikeReservationTest() {
    it('Debe existir la reserva', function() {
        const user = new Usuario({nombre: 'Luis', password: 'miSuperPass1287word', email: 'luis@yo.com'});
        user.save();
        let bike = new Bicicleta({code: 1, color: 'verde', modelo: 'urbana'});
        bike.save();
        let today = new Date();
        let tomorrow = new Date();
        tomorrow.setDate(today.getDate()+1);
        user.reservar(
            bike.id,
            today,
            tomorrow,
            function(err, reserva) {
                Reserva.find({}).populate('bicicleta').populate('usuario').exec(function(reservas) {
                    assert.equal(reservas.length, 1);
                    assert.equal(reservas[0].diasDeReserva(), 2);
                    assert.equal(reservas[0].bicicleta.code, 1);
                    assert.equal(reservas[0].usuario.nombre, user.nombre);
                })
        });
    });
}
