/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.web.Controlador;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.domain.Producto;
import com.example.domain.Usuarios;
import com.example.web.Respositorios.DetalleRepositorio;
import com.example.web.Services.ProductoService;
import com.example.web.Services.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@Slf4j
@SessionAttributes({ "nombre", "id", "rol" })

public class ControladorREST {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DetalleRepositorio detalleRepositorio;

    @Autowired
    private ProductoService productoService;

    int contador = 0;

    @GetMapping("/")
    public String abrirIniciar_crear(
            @RequestParam(value = "error", required = false) String error,
            Model model) {

        if (error != null) {
            contador++;
        } else {
            contador = 0;
        }

        if (contador >= 3) {
            model.addAttribute("bloquear", true);
        }
        return "Iniciar_crear";
    }

    @GetMapping("/acceder")
    public String iniciarSesion(HttpSession session,
            Model model) {

        Optional<Usuarios> usuario1 = usuarioService.obtenerUsuarioPorId((int) (session.getAttribute("id")));
        Usuarios user = usuario1.get();

        session.setAttribute("nombre", user.getNombre());
        session.setAttribute("rol", user.getRol());
        return "redirect:/index";
    }

    @GetMapping("/cerrar")
    public String cerrar_sesion(HttpSession request, SessionStatus status) {
        status.setComplete();
        request.removeAttribute("nombre");
        request.removeAttribute("id");
        request.removeAttribute("rol");
        return "redirect:/";
    }

    @GetMapping("/index")
    public String menu(Model model) {
        int conta = 1;

        List<Map<String, Object>> topProductos = detalleRepositorio.top5();
        for (Map<String, Object> producto : topProductos) {
            Optional<Producto> producto1 = productoService
                    .obtenerProductoPorId(Integer.parseInt(producto.get("c_producto").toString()));
            Producto nuevo = producto1.get();
            model.addAttribute("nombre" + conta, nuevo.getNombre());
            model.addAttribute("producto" + conta, producto.get("n_cantidad_producto"));
            conta++;
        }
        return "index";
    }

    @PostMapping("/fecha")
    public String fecha(Model model,
            @RequestParam("fecha") String fecha) {

        int conta = 1;

        List<Map<String, Object>> topProductos = detalleRepositorio.top5porFecha(fecha);
        for (Map<String, Object> producto : topProductos) {
            Optional<Producto> producto1 = productoService
                    .obtenerProductoPorId(Integer.parseInt(producto.get("c_producto").toString()));
            Producto nuevo = producto1.get();
            model.addAttribute("nombre" + conta, nuevo.getNombre());
            model.addAttribute("producto" + conta, producto.get("n_cantidad_producto"));
            conta++;
        }
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }
}
