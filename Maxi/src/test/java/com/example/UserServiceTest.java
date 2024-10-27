package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Usuarios;
import com.example.web.Services.UsuarioService;

import java.lang.NullPointerException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UsuarioService userService;

    @Test
    void testAgregarUsuario() {
        String email = "ped24@gmail.com";
        String contraseña = "Dsa@#$%32532";
        userService.agregarUsuario("Empleado", email, "Pedro", contraseña);
        assertEquals(userService.obtenerUsuarioPorEmailContraseñaEncriptada(email, contraseña),
                userService.obtenerUsuarioPorEmailContraseñaEncriptada(email, contraseña));
    }

    @Test
    void testEliminar() {
        String email = "ped24@gmail.com";
        String contraseña = "Dsa@#$%32532";

        Usuarios usuario = userService.obtenerUsuarioPorEmailContraseñaEncriptada(email, contraseña);
        assertNotNull(usuario, "El usuario debe existir antes de eliminarlo");

        userService.eliminarUsuario(usuario.getIdUsuario());


        assertThrows(NullPointerException.class, () ->{
            userService.obtenerUsuarioPorEmailContraseñaEncriptada(email, contraseña);
        });
    }
}
