package com.example.web.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.web.Services.ProductoService;

@Controller
@SessionAttributes({ "nombre", "id", "rol" })
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/stock")
    public String stock(Model model) {
        model.addAttribute("categorias", productoService.obtenerCategorias());
        model.addAttribute("productos", productoService.obtenerProductos());
        model.addAttribute("proveedores", productoService.obtenerProveedores());
        return "stock";
    }


    @GetMapping("/productos")
    public String productos(Model model) {
        model.addAttribute("categorias", productoService.obtenerCategorias());
        model.addAttribute("productos", productoService.obtenerProductos());
        model.addAttribute("proveedores", productoService.obtenerProveedores());
        return "/productos";
    }

    @PostMapping("/agregar_prod")
    public String agregarProd(
            @RequestParam("categoria") String nombre_categoria,
            @RequestParam("proveedor") String nombre_proveedor,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("nombre") String nombre,
            @RequestParam("precio") double precio,
            @RequestParam("stock") int stock,
            Model model) {
        ;
        productoService.agregarProducto(nombre_categoria, nombre_proveedor, descripcion, nombre, precio, stock);
        model.addAttribute("categorias", productoService.obtenerCategorias());
        model.addAttribute("productos", productoService.obtenerProductos());
        model.addAttribute("proveedores", productoService.obtenerProveedores());
        return "redirect:/productos";
    }

    @PostMapping("/actualizar_prod")
    public String actualizarProd(
            @RequestParam("categoria_act") String nombre_categoria,
            @RequestParam("proveedor_act") String nombre_proveedor,
            @RequestParam("descripcion_act") String descripcion,
            @RequestParam("nombre_act") String nombre,
            @RequestParam("precio_act") double precio,
            @RequestParam("stock_act") int stock,
            @RequestParam("id_act") int id_producto,
            Model model) {

        productoService.actualizarProducto(nombre_categoria, nombre_proveedor, descripcion, nombre, precio, stock,
                id_producto);
        model.addAttribute("categorias", productoService.obtenerCategorias());
        model.addAttribute("productos", productoService.obtenerProductos());
        model.addAttribute("proveedores", productoService.obtenerProveedores());
        return "redirect:/productos";
    }

    @PostMapping("/eliminar_prod")
    public String eliminarProd(
            @RequestParam("id_eliminar") int id_producto,
            Model model) {

        productoService.eliminarProducto(id_producto);

        model.addAttribute("categorias", productoService.obtenerCategorias());
        model.addAttribute("productos", productoService.obtenerProductos());
        model.addAttribute("proveedores", productoService.obtenerProveedores());
        return "redirect:/productos";

    }
}