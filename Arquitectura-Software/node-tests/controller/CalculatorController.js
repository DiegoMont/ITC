const express = require('express');
const CalculatorService = require('../service/calculator');

const router = new express.Router();

router.get('/add/:a/:b', (req, res) => {
    const a = Number(req.params.a);
    const b = Number(req.params.b);
    const result = {};
    result.result = CalculatorService.add(a, b);
    res.send(result);
});

router.post('/add', (req, res) => {
    console.log(req.body);
    const a = Number(req.body.a);
    const b = Number(req.body.b);
    const result = {};
    result.result = CalculatorService.add(a, b);
    res.send(result);
});

router.get('/substract/:a/:b', (req, res) => {
    const a = Number(req.params.a);
    const b = Number(req.params.b);
    const result = {};
    result.result = CalculatorService.substract(a, b);
    res.send(result);
});

router.post('/substract', (req, res) => {
    const a = Number(req.body.a);
    const b = Number(req.body.b);
    const result = {};
    result.result = CalculatorService.substract(a, b);
    res.send(result);
});

router.get('/divide/:a/:b', (req, res) => {
    const a = Number(req.params.a);
    const b = Number(req.params.b);
    const result = {};
    result.result = CalculatorService.divide(a, b);
    res.send(result);
});

router.post('/divide', (req, res) => {
    const a = Number(req.body.a);
    const b = Number(req.body.b);
    const result = {};
    result.result = CalculatorService.divide(a, b);
    res.send(result);
});

router.get('/multiply/:a/:b', (req, res) => {
    const a = Number(req.params.a);
    const b = Number(req.params.b);
    const result = {};
    result.result = CalculatorService.multiply(a, b);
    res.send(result);
});

router.post('/multiply', (req, res) => {
    const a = Number(req.body.a);
    const b = Number(req.body.b);
    const result = {};
    result.result = CalculatorService.multiply(a, b);
    res.send(result);
});

module.exports = router;
