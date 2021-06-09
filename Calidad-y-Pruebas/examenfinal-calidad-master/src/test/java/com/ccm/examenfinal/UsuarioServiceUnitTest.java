package com.ccm.examenfinal;

import com.ccm.examenfinal.model.Usuario;
import com.ccm.examenfinal.service.UsuarioService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioServiceUnitTest extends ExamenfinalApplicationTests {
    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void registrarUsuario() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setNombre("Diego");
        usuario.setCorreoElectronico("diego@epic.io");
        usuario.setPassword("pongame100");
        assertEquals(usuario, usuarioService.registraUsuario(usuario));
    }

    @Test
    public void loginUsuario() throws Exception{
        Usuario usuario = new Usuario();
        usuario.setNombre("nombreSeguro");
        usuario.setCorreoElectronico("examenfinal@gmail.com");
        usuario.setPassword("passwordSeguro");
        assertEquals("TOKENSEGURO", usuarioService.login(usuario));
    }
}
