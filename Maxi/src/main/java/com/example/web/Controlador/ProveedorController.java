package com.example.web.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.web.Services.ProveedoresService;


@Controller
public class ProveedorController {

    @Autowired
    private ProveedoresService proveedoresService;

    @GetMapping("/proveedores")
    public String proveedores(Model model) {
        model.addAttribute("proveedores", proveedoresService.obtenerProveedores());
        return "proveedores";
    }

    @PostMapping("/agregar_prov")
    public String agregarProv(
            @RequestParam("nombre") String nombre,
            @RequestParam("direccion") String direccion,
            @RequestParam("telefono") int telefono,
            @RequestParam("email") String email,
            Model model) {

        proveedoresService.agregarProveedor(nombre, direccion, telefono, email);

        model.addAttribute("proveedores", proveedoresService.obtenerProveedores());

        return "proveedores";
    }

    @PostMapping("/actualizar_prov")
    public String actualizarProv(
            @RequestParam("nombre_act") String nombre,
            @RequestParam("direccion_act") String direccion,
            @RequestParam("telefono_act") int telefono,
            @RequestParam("email_act") String email,
            @RequestParam("id_act") int id_proveedor,
            Model model) {

        proveedoresService.actualizarProveedor(nombre, direccion, telefono, email, id_proveedor);

        model.addAttribute("proveedores", proveedoresService.obtenerProveedores());

        return "proveedores";
    }

    @PostMapping("/eliminar_prov")
    public String eliminarProv(
            @RequestParam("id_eliminar") int id_proveedor,
            Model model) {

        proveedoresService.eliminarProveedor(id_proveedor);

        model.addAttribute("proveedores", proveedoresService.obtenerProveedores());

        return "proveedores";

    }
}