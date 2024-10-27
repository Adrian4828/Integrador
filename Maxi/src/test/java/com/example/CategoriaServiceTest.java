package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Categoria;
import com.example.web.Services.CategoriaService;

@SpringBootTest
class CategoriaServiceTest {
    @Autowired
    private CategoriaService categoriaService;

    @Test
    void testAgregarCategoria() {
        String nombre = "Muebles";
        String descripcion = "Colección de muebles para hogar";
        categoriaService.agregarCategoria(nombre, descripcion);
        assertEquals(categoriaService.obtenerCategoriaPorNombre(nombre),
                categoriaService.obtenerCategoriaPorNombre(nombre));
    }


    @Test
    void testEliminar() {

        // Obtén la categoría a eliminar
        Categoria categoria = categoriaService.obtenerCategoriaPorNombre("Muebles");
        assertNotNull(categoria, "La categoría debe existir antes de eliminarla");

        // Elimina la categoría
        categoriaService.eliminarCategoria(categoria.getIdCategoria());

        // Verifica que la categoría ha sido eliminada
        Categoria categoriaEliminada = categoriaService.obtenerCategoriaPorNombre("Muebles");
        assertNull(categoriaEliminada, "La categoría debería haber sido eliminada");

        assertEquals(null, categoriaEliminada);
    }
}
