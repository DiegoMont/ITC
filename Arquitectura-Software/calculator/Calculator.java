public class Calculator {
    public Calculator() {}

    public double add(double a, double b) {
        return a + b;
    }

    public double divide(double a, double b) throws ArithmeticException {
        if(b == 0)
            throw new ArithmeticException();
        return a / b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double substract(double a, double b) {
        return a - b;
    }

    public double power(double base, double exponent) {
        int integerExponent = (int) exponent;
        boolean isIntegerExponent = integerExponent == exponent;
        if(isIntegerExponent) {
            if(integerExponent < 0)
                return exponentiationBySquaring(1/base, -integerExponent);
            else
                return exponentiationBySquaring(base, integerExponent);
        } else
            return Math.pow(base, exponent);
    }

    private double exponentiationBySquaring(double base, int exponent) {
        if(exponent == 0)
            return 1;
        boolean isEvenExponent = (exponent & 1) == 0;
        if(isEvenExponent)
            return exponentiationBySquaring(base * base, exponent/2);
        else
            return base * exponentiationBySquaring(base * base, (exponent-1)/2);
    }

}