
const assert = require('assert');

const Calculator = require('../service/calculator');

describe('Node Web Calculator', () => {
    describe('Addition', addTest);
    describe('Subsctraction', substractTest);
    describe('Divison', divideTest);
    describe('Multiplication', multiplyTest);
})

function addTest() {
    it('1 + 2 = 3', () => {
            assert.equal(Calculator.add(1, 2), 3);
    })
}

function substractTest() {
    it('18 - 9 = 9', () => {
        assert.equal(Calculator.substract(18, 9), 9);
    })
}

function divideTest() {
    it('20 / 4 = 5', () => {
        assert.equal(Calculator.divide(20, 4), 5);
    })
}

function multiplyTest() {
    it('11 x 2 = 22', () => {
        assert.equal(Calculator.multiply(11, 2), 22);
    })
}
