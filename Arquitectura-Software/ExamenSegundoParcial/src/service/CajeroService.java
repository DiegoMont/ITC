package service;

import model.Banco;

/**
 * Debido a que los retiros o depósitos se hacen a través de un cajero (ATM) y este cajero puede ser del mismo banco al que pertenece la cuenta o diferente.
 * Se implementó esta clase que funciona como adaptador para calcular la comisión necesaria que se debe cobrar por realizar el movimiento bancario.
 * Con los modificadores de acceso que tiene CuentaService aseguramos que la única forma de realizar y registrar una de estas dos operaciones sea a traves de esta clase.
 * 
 */
public class CajeroService {

    CuentaService cuentaService;

    public CajeroService() {
        cuentaService = new CuentaService();
    }

    public void depositar(int indiceCuenta, double monto) {
        cuentaService.depositar(indiceCuenta, monto);
    }

    public void retirar(Banco bancoCajero, int indiceCuenta, double monto) throws Exception {
        Banco bancoCuenta = cuentaService.getCuenta(indiceCuenta).getBanco();
        if(bancoCajero == bancoCuenta)
            cuentaService.retirar(indiceCuenta, monto);
        else if(bancoCajero == Banco.BANCO_A)
            cuentaService.retirar(indiceCuenta, monto, 30);
        else if(bancoCajero == Banco.BANCO_B)
            cuentaService.retirar(indiceCuenta, monto, 15);
        else if(bancoCajero == Banco.BANCO_C)
            cuentaService.retirar(indiceCuenta, monto, 10);
    }
}
