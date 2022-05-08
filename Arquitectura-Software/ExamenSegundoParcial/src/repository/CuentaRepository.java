package repository;

import model.Cuenta;

import java.util.ArrayList;
import java.util.List;

public class CuentaRepository {

    private List<Cuenta> cuentas;

    CuentaRepository(){
        cuentas = new ArrayList<>();
    }

    Cuenta obtenerCuentaPorIndice(int index){
        return cuentas.get(index);
    }

    void agregarCuenta(Cuenta c){
        cuentas.add(c);
    }

    Cuenta obtenerCuentaPorNumeroCuenta(String numeroDeCuenta){
        for (Cuenta c:
             cuentas) {
            if(c.getNumeroDeCuenta().equalsIgnoreCase(numeroDeCuenta)){
                return c;
            }
        }
        return null;
    }

    void actualizarMontoDeCuenta(int index,double monto){
        //ToDO
    }


}
