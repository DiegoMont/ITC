package com.examen.segundoparcial;

import com.examen.segundoparcial.service.OperacionesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperacionesServiceUnitTest extends SegundoparcialApplicationTests {
    @Autowired
    private OperacionesService operacionesService;

    @Test
    public void sumaTest(){
        assertEquals(20, operacionesService.suma(13, 7));
    }

    @Test
    public void restaTest(){
        assertEquals(-10, operacionesService.resta(5, 15));
    }

    @Test
    public void multiplicacionTest(){
        assertEquals(Math.PI * Math.PI, operacionesService.multiplicacion(Math.PI, Math.PI));
    }

    @Test
    public void divivionTest(){
        assertEquals(5, operacionesService.division(60, 12));
    }
}
