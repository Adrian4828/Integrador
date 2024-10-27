/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.web.Controlador;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.domain.Usuarios;
import com.example.web.Services.UsuarioService;


@Controller
@Slf4j
@SessionAttributes({ "nombre", "id", "rol" })

public class ControladorREST {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/")
    public String comienzo(Model model) {
        return "Iniciar_crear";
    }

    @PostMapping("/")
    public String iniciarSesion(
            @RequestParam("email") String email,
            @RequestParam("contraseña") String contraseña,
            Model model) {

        // Buscar al usuario en la base de datos por email y contraseña
        Usuarios usuario = usuarioService.obtenerUsuarioPorEmailContraseñaEncriptada(email, contraseña);

        if (usuario != null) {
            // Si el usuario existe, redirigir a la página de éxito
            model.addAttribute("nombre", usuario.getNombre());
            model.addAttribute("id", usuario.getIdUsuario());
            model.addAttribute("rol", usuario.getRol());
            return "index";
        } else {
            // Si las credenciales son incorrectas, mostrar un error
            model.addAttribute("error", "Credenciales incorrectas");
            return "Iniciar_crear";
        }
    }

    @GetMapping("/nuevaVenta")
    public String abrirnuevaVenta(Model model) {
        return "nuenvaVenta";
    }

    @GetMapping("/index")
    public String menu(Model model) {
        return "index";
    }

    @GetMapping("/stock")
    public String stock(Model model) {
        return "stock";
    }

    @GetMapping("/pedidos")
    public String pedido(Model model) {
        return "pedidos";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }

}
