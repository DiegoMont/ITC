package com.examen.segundoparcial;

import com.examen.segundoparcial.models.Circulo;
import com.examen.segundoparcial.models.Cuadrado;
import com.examen.segundoparcial.models.OperacionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class FigurasIntegrationTest extends SegundoparcialApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calcularAreaCirculoTest() throws Exception {
        Circulo circulo = new Circulo();
        circulo.setRadio(1);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(circulo);
        mockMvc.perform(post("/v1/circulo/area").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(Math.PI));
    }

    @Test
    public void calcularAreaCuadradoTest() throws Exception {
        Cuadrado cuadrado = new Cuadrado();
        cuadrado.setLado(11);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(cuadrado);
        mockMvc.perform(post("/v1/cuadrado/area").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(121));
    }
}
