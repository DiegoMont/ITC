package test;

import org.junit.jupiter.api.Test;

import model.Banco;
import model.Cuenta;
import service.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTests {

    private CuentaService cuentaService;
    private CajeroService cajeroService;

    @Test
    public void buscarPorIndice() {
        try {
            String nombre = "Diego";
            Banco banco = Banco.BANCO_A;
            double saldoInicial = 200;
            Cuenta cuenta = cuentaService.abrirCuenta(nombre, banco, saldoInicial);
            Cuenta cuentaPorIndice = cuentaService.getCuenta(1);
            assertEquals(cuenta, cuentaPorIndice);
        } catch (Exception e) {}
    }

    @Test
    public void buscarPorNumCuenta() {
        try {
            String nombre = "Diego";
            Banco banco = Banco.BANCO_A;
            double saldoInicial = 200;
            Cuenta cuenta = cuentaService.abrirCuenta(nombre, banco, saldoInicial);
            String numeroCuenta = cuenta.getNumeroDeCuenta();
            int indiceNuevaCuenta = cuentaService.getIndiceCuenta(numeroCuenta);
            Cuenta cuentaPorIndice = cuentaService.getCuenta(indiceNuevaCuenta);
            assertEquals(cuenta, cuentaPorIndice);
        } catch (Exception e) {}
    }

    @Test
    public void hacerDeposito() {
        try {
            String nombre = "Diego";
            Banco banco = Banco.BANCO_A;
            double saldoInicial = 200;
            Cuenta cuenta = cuentaService.abrirCuenta(nombre, banco, saldoInicial);
            double montoADepositar = 100;
            cajeroService.depositar(1, montoADepositar);
            double nuevoSaldo = saldoInicial + montoADepositar;
            assertEquals(cuenta.getMonto(), nuevoSaldo);
        } catch (Exception e) {}
    }

    @Test
    public void hacerRetiro() {
        try {
            String nombre = "Diego";
            Banco banco = Banco.BANCO_A;
            double saldoInicial = 200;
            Cuenta cuenta = cuentaService.abrirCuenta(nombre, banco, saldoInicial);
            double montoRetiro = 100;
            double comision = 15;
            cajeroService.retirar(Banco.BANCO_B, 1, montoRetiro);
            double nuevoSaldo = saldoInicial - montoRetiro - comision;
            assertEquals(cuenta.getMonto(), nuevoSaldo);
        } catch (Exception e) {}
    }

}
