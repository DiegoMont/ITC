package com.examen.segundoparcial;

import com.examen.segundoparcial.models.OperacionRequest;
import com.examen.segundoparcial.service.OperacionesService;
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
public class OperacionesIntegrationTest extends SegundoparcialApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void sumaTest() throws Exception {
        OperacionRequest operacionRequest = new OperacionRequest();
        operacionRequest.setA(2);
        operacionRequest.setB(1);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/v1/suma").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(3));
    }

    @Test
    public void restaTest() throws Exception{
        OperacionRequest operacionRequest = new OperacionRequest();
        operacionRequest.setA(2);
        operacionRequest.setB(1);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/v1/resta").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(1));
    }

    @Test
    public void multiplicacionTest() throws Exception {
        OperacionRequest operacionRequest = new OperacionRequest();
        operacionRequest.setA(2);
        operacionRequest.setB(1);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/v1/multiplicacion").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(2));
    }

    @Test
    public void divisionTest() throws Exception {
        OperacionRequest operacionRequest = new OperacionRequest();
        operacionRequest.setA(2);
        operacionRequest.setB(1);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/v1/division").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$").value(2));
    }

    @Test
    public void divisionCeroTest() throws Exception {
        OperacionRequest operacionRequest = new OperacionRequest();
        operacionRequest.setA(2);
        operacionRequest.setB(0);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(operacionRequest);
        mockMvc.perform(post("/vi/division").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is5xxServerError());
    }
}
