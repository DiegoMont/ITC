package view;

import java.util.Scanner;

import model.Banco;
import model.Cuenta;
import service.CuentaService;

public class CliMenu {

    private Scanner sc;
    private CuentaService service;

    public CliMenu() {
        sc = new Scanner(System.in);
        service = new CuentaService();
    }

    public void startApp() {
        System.out.println("Bienvenido al Sistema Financiero Nacional");
        int input = 0;
        while(input != 4) {
            printMainMenu();
            input = Integer.parseInt(sc.nextLine());
            try {
                if(input == 1)
                    crearCuenta();
                else if(input == 2)
                    buscarCuentaPorIndice();
                else if(input == 3)
                    buscarPorNumeroDeCuenta();
            } catch(Exception e) {
                System.out.println("Esta operacion no es valida!");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("1. Crear cuenta");
        System.out.println("2. Buscar cuenta (indice)");
        System.out.println("3. Cuscar cuenta (numero de cuenta)");
        System.out.println("4. Salir");
        System.out.print("Ingrese una opcion: ");
    }

    private void crearCuenta() throws Exception {
        System.out.print("Cual es su nombre: ");
        String nombre = sc.nextLine();
        Banco banco = seleccionarBanco();
        System.out.print("Cual es el saldo inicial: ");
        double saldoInicial = Double.parseDouble(sc.nextLine());
        Cuenta nuevaCuenta = service.abrirCuenta(nombre, banco, saldoInicial);
        System.out.println("La cuenta " + nuevaCuenta.getNumeroDeCuenta() + " ha sido creada con exito!");
    }

    private void buscarCuentaPorIndice() {
        int totalCuentas = service.countCuentas();
        System.out.print("Ingrese el indice de la cuenta[1-" + totalCuentas + "]: ");
        int indiceCuenta = Integer.parseInt(sc.nextLine()) - 1;
        Cuenta cuenta = service.getCuenta(indiceCuenta);
        abrirMenuCuenta(cuenta);
    }

    private void buscarPorNumeroDeCuenta() {
        System.out.print("Ingrese el numero de cuenta: ");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = service.getCuenta(numeroCuenta);
        abrirMenuCuenta(cuenta);
    }

    private Banco seleccionarBanco() {
        int input = 0;
        while(input < 1 || input > 3) {
            System.out.println("1. Banco A");
            System.out.println("2. Banco B");
            System.out.println("3. Banco C");
            System.out.print("Seleccione un banco: ");
            input = Integer.parseInt(sc.nextLine());
        }
        if(input == 1)
            return Banco.BANCO_A;
        if(input == 2)
            return Banco.BANCO_B;
        return Banco.BANCO_C;
    }

    private void abrirMenuCuenta(Cuenta c) {
        int input = 0;
        while(input < 1 || input > 3) {
            printSaldo(c);
            printMenuCuenta();
            input = Integer.parseInt(sc.nextLine());
        }
    }

    private void printSaldo(Cuenta c) {
        System.out.println("El saldo actual de la cuenta " + c.getNumeroDeCuenta() + " es de");
        System.out.println(c.getMonto() + " solidos pesos");
    }

    private void printMenuCuenta() {
        System.out.println("1. Depositar");
        System.out.println("2. Hacer un retiro");
        System.out.println("3. Regresar");
        System.out.print("Ingrese una opcion: ");
    }
}
