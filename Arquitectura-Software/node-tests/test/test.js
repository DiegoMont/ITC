const axios = require('axios');
const assert = require('assert');

const BASE_URL = 'http://localhost:4321/calculate';

describe('Node Web Calculator', () => {
    describe('Addition', addTest);
    describe('Subsctraction', substractTest);
    describe('Divison', divideTest);
    describe('Multiplication', multiplyTest);
})

function addTest() {
    it('1 + 2 = 3', () => {
        axios.get(BASE_URL + '/add/1/2').then((response) => {
            const res = JSON.parse(response.data);
            assert.equal(res.result, 3)
        });
    })
}

function substractTest() {
    it('18 - 9 = 9', () => {
        axios.get(BASE_URL + '/substract/18/9').then((response) => {
            const res = JSON.parse(response.data);
            assert.equal(res.result, 9)
        });
    })
}

function divideTest() {
    it('20 / 4 = 5', () => {
        axios.get(BASE_URL + '/divide/20/4').then((response) => {
            const res = JSON.parse(response.data);
            assert.equal(res.result, 3)
        });
    })
}

function multiplyTest() {
    it('11 x 2 = 22', () => {
        axios.get(BASE_URL + '/multiply/11/2').then((response) => {
            const res = JSON.parse(response.data);
            assert.equal(res.result, 22)
        });
    })
}
