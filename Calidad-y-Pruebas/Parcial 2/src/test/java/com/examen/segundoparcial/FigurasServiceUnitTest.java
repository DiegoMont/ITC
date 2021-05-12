package com.examen.segundoparcial;

import com.examen.segundoparcial.models.Circulo;
import com.examen.segundoparcial.models.Cuadrado;
import com.examen.segundoparcial.service.FigurasService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FigurasServiceUnitTest extends SegundoparcialApplicationTests{
    @Autowired
    private FigurasService figurasService;

    @Test
    public void calcularAreaCirculo(){
        Circulo circulo = new Circulo();
        circulo.setRadio(1);
        assertEquals(Math.PI, figurasService.calcularArea(circulo));
    }

    @Test
    public void calcularAreaCuadrado(){
        Cuadrado cuadrado = new Cuadrado();
        cuadrado.setLado(13);
        assertEquals(169, figurasService.calcularArea(cuadrado));
    }
}
