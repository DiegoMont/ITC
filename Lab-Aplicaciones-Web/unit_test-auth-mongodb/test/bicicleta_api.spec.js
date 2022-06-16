const axios = require('axios')
const assert = require('assert');

const BASE_URL = 'http://localhost:3000/api/bicicletas/';

describe('Bicicletas API', function() {
    describe('GET BICICLETAS /', getBicisTest);
    describe('POST BICICLETA /create', addBiciTest);
});

function getBicisTest() {
    it('Status-code: 200', function() {
        axios.get(BASE_URL).then(function(response) {
            const res = JSON.parse(response.data);
            assert.equal(response.status, 200);
            const bikeCount = res.bicicletas.length;
            assert.equal(bikeCount, 0)
        }).catch(function(err) {
            //console.log(err);
        });
    });
};

function addBiciTest() {
    it('Status-code: 200', function() {
        const bici = {
            code: 3,
            color: 'green',
            modelo: 'bmx',
            lat: -99.13,
            lon: 19.28
        }
        axios.post(BASE_URL+'create', bici).then(function(response) {
            assert.equal(response.status, 200);
        }).catch(function(err) {});
    });
}
