const Bicicleta = require('../models/bicicleta');
const assert = require('assert');

describe('Testing bicicletas', function() {
    describe('Bicicleta.createInstance', createInstanceTest);
    describe('Bicicleta.allBicis', allBicisTest);
    describe('Bicicleta.add', addTest);
    describe('Find bike by code', findByCodeTest);
    describe('Remove bike by code', removeByCodeTest);
})

function createInstanceTest() {
    it('Crea una instancia de la bicicleta', function() {
        const CODE = 1;
        const COLOR = 'verde';
        const BRAND = 'urbana';
        const LATITUDE = 19.28;
        const LONGITUDE = -99.13;
        const bike = Bicicleta.createInstance(CODE, COLOR, BRAND, [LATITUDE, LONGITUDE]);
        assert.equal(bike.code, CODE);
        assert.equal(bike.color, COLOR);
        assert.equal(bike.modelo, BRAND);
        assert.equal(bike.ubicacion[0], LATITUDE);
        assert.equal(bike.ubicacion[1], LONGITUDE);
    });
}

function allBicisTest() {
    it('Comienza vacía', function() {
        Bicicleta.allBicis(function(err, bicis) {
            assert.equal(bicis.length, 0);
        })
    });
}

function addTest() {
    it('Agrega una bici', function() {
        let bike = new Bicicleta({code: 1, color: 'verde', modelo: 'urbana'});
        Bicicleta.add(bike, function(err, newBici) {
            if(err)
                console.log(err);
            Bicicleta.allBicis(function(err, bicis) {
                assert.equal(bicis.length, 1);
                assert.equal(bicis[0].code, bike.code);
            });
        });
    });
}

function findByCodeTest() {
    it('should return bike with code 1', function() {
        Bicicleta.allBicis(function(err, bicis) {
            assert.equal(bicis.length, 0);
            const bici = new Bicicleta({code: 1, color: 'verde', modelo: 'urbana'});
            Bicicleta.add(bici, function(err, newBike){
                if(err)
                    console.log(err);
                Bicicleta.findByCode(1, function(err, targetBici){
                    assert.equal(targetBici.code, bici.code);
                    assert.equal(targetBici.color, bici.color);
                    assert.equal(targetBici.modelo, bici.modelo);
                });
            });
        })
    });
}

function removeByCodeTest() {
    it('Delete bike with code 1', function() {
        Bicicleta.allBicis(function(err, bicis) {
            assert.equal(bicis.length, 0);
            const bici = new Bicicleta({code: 1, color: 'verde', modelo: 'urbana'});
            Bicicleta.add(bici, function(err, newBike){
                if(err)
                    console.log(err);
                Bicicleta.allBicis(function(err, bicis) {
                    assert.equal(bicis.length, 1);
                    Bicicleta.removeByCode(1, function(err, cb){
                        Bicicleta.allBicis(function(err, bicis) {
                            assert.equal(bicis.length, 0);
                        });
                    });
                });
            });
        });
    });
}
