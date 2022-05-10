package view;

import java.util.Scanner;

import model.Banco;
import model.Cuenta;
import service.CajeroService;
import service.CuentaService;

public class CliMenu {

    private Scanner sc;
    private CuentaService cuentaService;
    private CajeroService cajeroService;

    public CliMenu() {
        sc = new Scanner(System.in);
        cuentaService = new CuentaService();
        cajeroService = new CajeroService();
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
        System.out.println("3. Buscar cuenta (numero de cuenta)");
        System.out.println("4. Salir");
        System.out.print("Ingrese una opcion: ");
    }

    private void crearCuenta() throws Exception {
        System.out.print("Cual es su nombre: ");
        String nombre = sc.nextLine();
        /* System.out.println("1. Banco A");
        System.out.println("2. Banco B");
        System.out.println("3. Banco C");
        System.out.print("Seleccione un banco: ");
        int inputBanco = Integer.parseInt(sc.nextLine());
        if(inputBanco == 1)
            banco = Banco.BANCO_A;
        else if(inputBanco == 2)
            banco =  Banco.BANCO_B;
        else
            banco = Banco.BANCO_C;
        */
        Banco banco = seleccionarBanco();
        System.out.print("Cual es el saldo inicial: ");
        double saldoInicial = Double.parseDouble(sc.nextLine());
        Cuenta nuevaCuenta = cuentaService.abrirCuenta(nombre, banco, saldoInicial);
        System.out.println("La cuenta " + nuevaCuenta.getNumeroDeCuenta() + " ha sido creada con exito!");
        printSaldo(nuevaCuenta);
    }

    private void buscarCuentaPorIndice() {
        int totalCuentas = cuentaService.countCuentas();
        System.out.print("Ingrese el indice de la cuenta[1-" + totalCuentas + "]: ");
        int indiceCuenta = Integer.parseInt(sc.nextLine()) - 1;
        abrirMenuCuenta(indiceCuenta);
    }

    private void buscarPorNumeroDeCuenta() {
        System.out.print("Ingrese el numero de cuenta: ");
        String numeroCuenta = sc.nextLine();
        int indiceCuenta = cuentaService.getIndiceCuenta(numeroCuenta);
        abrirMenuCuenta(indiceCuenta);
    }

    /**
     * En varias ocasiones es necesario elegir uno de los tres bancos disponibles. Por lo que se un hizo un refactor en el que que se reemplaza el código repetido por esta función
     */
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

    private void abrirMenuCuenta(int indiceCuenta) {
        int input = 0;
        while(input != 3) {
            printSaldo(cuentaService.getCuenta(indiceCuenta));
            printMenuCuenta();
            input = Integer.parseInt(sc.nextLine());
            try {
                if(input == 1)
                    hacerDeposito(indiceCuenta);
                else if(input == 2)
                    hacerRetiro(indiceCuenta);
            } catch(Exception e) {
                System.out.println("Revisa la informacion de la cuenta");
            }
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

    private void hacerDeposito(int indice) {
        System.out.print("Ingresa el monto que deseas depositar: ");
        double monto = Double.parseDouble(sc.nextLine());
        cajeroService.depositar(indice, monto);
    }

    private void hacerRetiro(int indice) throws Exception {
        Banco bancoCajero = seleccionarBanco();
        /* int input = 0;
        System.out.print("A que banco pertenece el cajero: ");
        while(input < 1 || input > 3) {
            System.out.println("1. Banco A");
            System.out.println("2. Banco B");
            System.out.println("3. Banco C");
            System.out.print("Seleccione un banco: ");
            input = Integer.parseInt(sc.nextLine());
        }
        if(input == 1)
            bancoCajero = Banco.BANCO_A;
        else if(input == 2)
            bancoCajero = Banco.BANCO_B;
        else
            bancoCajero = Banco.BANCO_C; */
        System.out.print("Ingresa el monto que deseas retirar: ");
        double monto = Double.parseDouble(sc.nextLine());
        cajeroService.retirar(bancoCajero, indice, monto);
    }
}
