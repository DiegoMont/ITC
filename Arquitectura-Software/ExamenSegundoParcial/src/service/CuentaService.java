package service;

import model.Cuenta;
import model.Banco;
import repository.CuentaRepository;

public class CuentaService {

    CuentaRepository cuentaRepository;

    public CuentaService() {
        cuentaRepository = CuentaRepository.getInstance();
    }

    void depositar(int index, double monto) {
        Cuenta cuenta = cuentaRepository.obtenerCuentaPorIndice(index);
        double currentBalance = cuenta.getMonto();
        double balanceAfterDeposit = currentBalance + monto;
        cuentaRepository.actualizarMontoDeCuenta(index, balanceAfterDeposit);
    }

    void retirar(int index, double monto, double comision) throws Exception {
        retirar(index, monto + comision);
    }

    void retirar(int index, double monto) throws Exception {
        Cuenta cuenta = cuentaRepository.obtenerCuentaPorIndice(index);
        double currentBalance = cuenta.getMonto();
        double balanceAfterWithdraw = currentBalance - monto;
        if(balanceAfterWithdraw < 0)
            throw new Exception();
        cuentaRepository.actualizarMontoDeCuenta(index, balanceAfterWithdraw);
    }

    public Cuenta abrirCuenta(String nombre, Banco banco, double saldoInicial) throws Exception {
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setBanco(banco);
        nuevaCuenta.setNombre(nombre);
        if(saldoInicial < 0)
            throw new Exception();
        nuevaCuenta.setMonto(saldoInicial);
        nuevaCuenta.setNumeroDeCuenta(getNumeroDeCuenta(nombre, banco));
        agregarCuenta(nuevaCuenta);
        return nuevaCuenta;
    }

    public int countCuentas() {
        return cuentaRepository.countCuentas();
    }

    public Cuenta getCuenta(int indice) {
        return cuentaRepository.obtenerCuentaPorIndice(indice);
    }

    public int getIndiceCuenta(String numeroDeCuenta) {
        return cuentaRepository.obtenerIndicePorNumeroCuenta(numeroDeCuenta);
    }

    private String getNumeroDeCuenta(String nombre, Banco banco) {
        String claveBanco = "" + banco.hashCode() % 1000;
        String claveCuenta = "" + nombre.hashCode() % 1000;
        String numeroDeCuenta = claveBanco + claveCuenta;
        return numeroDeCuenta;
    }

    private void agregarCuenta(Cuenta c) {
        cuentaRepository.agregarCuenta(c);
    }
}
