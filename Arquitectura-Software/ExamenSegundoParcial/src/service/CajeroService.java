package service;

import model.Banco;

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
