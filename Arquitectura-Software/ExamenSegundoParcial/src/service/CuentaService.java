package service;

import model.Cuenta;
import repository.CuentaRepository;

public class CuentaService {

    //Utilizar Singleton de Cuenta Repository para hacer uso de sus métodos e implementar todos los métodos faltantes

    CuentaRepository cuentaRepository;

    public CuentaService() {
        cuentaRepository = CuentaRepository.getInstance();
    }

    public void depositar(int index, double monto) {
        Cuenta cuenta = cuentaRepository.obtenerCuentaPorIndice(index);
        int currentBalance = cuenta.getMonto();
        int balanceAfterDeposit = currentBalance + monto;
        cuentaRepository.actualizarMontoDeCuenta(index, balanceAfterDeposit);
    }

    public void retirar(int index, double monto, double comision) throws Exception {
        retirar(index, monto + comision);
    }

    public void retirar(int index, double monto) throws Exception {
        Cuenta cuenta = cuentaRepository.obtenerCuentaPorIndice(index);
        int currentBalance = cuenta.getMonto();
        int balanceAfterWithdraw = currentBalance - monto;
        if(balanceAfterWithdraw < 0)
            throw new Exception();
        cuentaRepository.actualizarMontoDeCuenta(index, balanceAfterWithdraw);
    }

    public void agregarCuenta(Cuenta c) {
        cuentaRepository.agregarCuenta(c);
    }
}
