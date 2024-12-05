package com.example.web.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.web.Respositorios.CategoriaRepositorio;
import com.example.web.Services.CategoriaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@SessionAttributes({ "nombre", "id", "rol" })
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @GetMapping("/categoria")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "categoria";
    }

    @PostMapping("/agregar_cat")
    public String agregarCategoria(@RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            Model model) {
        categoriaService.agregarCategoria(nombre, descripcion);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "redirect:/categoria";
    }

    @PostMapping("/actualizar_cat")
    public String actualizarCategoria(@RequestParam("id_act") int id,
            @RequestParam("nombre_act") String nombre,
            @RequestParam("descripcion_act") String descripcion,
            Model model) {
        categoriaRepositorio.actualizarCat(nombre, descripcion, id);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "redirect:/categoria";
    }

    @PostMapping("/eliminar_cat")
    public String eliminarCategoria(@RequestParam("id_eliminar") int id_categoria,
            Model model) {
        categoriaService.eliminarCategoria(id_categoria);
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "redirect:/categoria";
    }
}