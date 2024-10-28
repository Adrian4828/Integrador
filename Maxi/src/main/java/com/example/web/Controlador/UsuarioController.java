package com.example.web.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web.Services.UsuarioService;


@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public String usuarios(Model model) {

        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());
        return "usuarios";
    }

    @PostMapping("/agregar_usu")
    public String agregarUsu(
            @RequestParam("rol") String rol,
            @RequestParam("email") String email,
            @RequestParam("nombre") String nombre,
            @RequestParam("contraseña") String contraseña,
            Model model) {

        usuarioService.agregarUsuario(rol, email, nombre, contraseña);

        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());

        return "redirect:/usuarios";
    }

    @PostMapping("/actualizar_usu")
    public String actualizarUsu(
            @RequestParam("rol_act") String rol,
            @RequestParam("email_act") String email,
            @RequestParam("nombre_act") String nombre,
            @RequestParam("contraseña_act") String contraseña,
            @RequestParam("id_act") int id_usuario,
            Model model) {

        usuarioService.actualizarUsuario(rol, email, nombre, contraseña, id_usuario);

        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());

        return "redirect:/usuarios";
    }

    @PostMapping("/eliminar_usu")
    public String eliminarUsu(
            @RequestParam("id_eliminar") int id_usuario,
            Model model) {

        usuarioService.eliminarUsuario(id_usuario);

        model.addAttribute("usuarios", usuarioService.obtenerUsuarios());

        return "redirect:/usuarios";

    }
}
