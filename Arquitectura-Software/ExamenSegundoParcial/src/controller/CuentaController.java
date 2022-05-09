import model.Cuenta;
import service.CuentaService;

public class CuentaController {

    CuentaService service;

    public CuentaController() {
        service = new CuentaService();
    }

    public void createCuenta(String nombre, Cuenta.Banco banco, int saldoInicial) {
        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setBanco(banco);
        nuevaCuenta.setNombre(nombre);
        nuevaCuenta.setMonto(saldoInicial);
        String claveBanco = "" + banco.hashCode() % 10000;
        String claveCuenta = "" + nombre.hashCode() % 10000;
        String numeroDeCuenta = claveBanco + claveCuenta;
        service.agregarCuenta(nuevaCuenta);
    }

    public void retirarDeCajero(int indiceCuenta, Cuenta.Banco banco, int monto) {
        Cuenta cuenta = service.
    } 

}
