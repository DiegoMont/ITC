package com.ccm.examenfinal;

import com.ccm.examenfinal.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
public class UsuariosIntegrationTest extends ExamenfinalApplicationTests {
    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registroUsuarioExitoso() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setNombre("Diego");
        usuario.setCorreoElectronico("diego@epic.io");
        usuario.setPassword("pongame100");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(usuario);
        mockMvc.perform(post("/v1/registro").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void registroUsuarioDatosIncorrectos() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setNombre("Diego");
        usuario.setPassword("pongame100");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(usuario);
        mockMvc.perform(post("/v1/registro").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is4xxClientError());
    }

    @Test
    public void registroUsuarioPasswordInseguro() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setNombre("Diego");
        usuario.setCorreoElectronico("diego@epic.io");
        usuario.setPassword("a");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String json = objectWriter.writeValueAsString(usuario);
        mockMvc.perform(post("/v1/registro").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().is5xxServerError());
    }
}
