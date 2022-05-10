package repository;

import model.Cuenta;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de guardar a largo plazo el estado de las entidades Cuenta.
 * Las entidades estarán almacenadas en una lista que es una variable de instancia de la clase. Para asegurar que sólo haya una instancia de esta clase, y las cuentas estén guardadas en esa instancia, se utilizó el patrón Singleton.
 */
public class CuentaRepository {

    private static CuentaRepository instance;
    private List<Cuenta> cuentas;

    private CuentaRepository(){
        cuentas = new ArrayList<>();
    }

    public static CuentaRepository getInstance() {
        if(instance == null)
            instance = new CuentaRepository();
        return instance;
    }

    public Cuenta obtenerCuentaPorIndice(int index){
        return cuentas.get(index);
    }

    public void agregarCuenta(Cuenta c){
        cuentas.add(c);
    }

    public int obtenerIndicePorNumeroCuenta(String numeroDeCuenta) {
        int i = 0;
        for (Cuenta c: cuentas) {
            if(c.getNumeroDeCuenta().equalsIgnoreCase(numeroDeCuenta))
                return i;
            i++;
        }
        return -1;
    }

    public void actualizarMontoDeCuenta(int index, double monto){
        Cuenta cuenta = instance.obtenerCuentaPorIndice(index);
        cuenta.setMonto(monto);
    }

    public int countCuentas() {
        return cuentas.size();
    }
}
