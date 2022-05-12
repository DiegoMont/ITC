const add = (a, b) => a + b;

const substract = (a, b) => a - b;

const multiply = (a, b) => a * b;

const divide = (a, b) => a / b;

const power = (base, exponent) => {
    let integerExponent = exponent - (exponent % 1);
    let isIntegerExponent = integerExponent == exponent;
    if(isIntegerExponent) {
        if(integerExponent < 0)
            return exponentiationBySquaring(1/base, -integerExponent);
        else
            return exponentiationBySquaring(base, integerExponent);
    } else
        return Math.pow(base, exponent);
}

const exponentiationBySquaring = (base, exponent) => {
    if(exponent == 0)
        return 1;
    const isEvenExponent = (exponent & 1) == 0;
    if(isEvenExponent)
        return exponentiationBySquaring(base * base, exponent/2);
    else
        return base * exponentiationBySquaring(base * base, (exponent-1)/2);
}

module.exports = { add, substract, multiply, divide, power };
