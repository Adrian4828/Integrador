package com.example;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Producto;
import com.example.web.Services.ProductoService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductosServiceTest {

    @Autowired
    private ProductoService productosServiceTest;

    @Test
    void testAgregarProductos() {
        String nombreCategoria = "Dulces y snacks";
        String nombreProveedor = "Marcos";
        String descripcion = "Comestible";
        String nombre = "Doritos";
        double precio = 3.50;
        int stock = 500;
        productosServiceTest.agregarProducto( nombreCategoria,  nombreProveedor,  descripcion,  nombre,  precio,  stock);
        assertEquals(productosServiceTest.obtenerProductoPorNombre(nombre),
            productosServiceTest.obtenerProductoPorNombre(nombre));
    }

    @Test
    void testEliminar() {
        
        String nombre = "Doritos";

        Producto producto = productosServiceTest.obtenerProductoPorNombre(nombre);
        assertNotNull(producto, "El producto debe existir antes de eliminarlo");

        productosServiceTest.eliminarProducto(producto.getIdProducto());

        Producto productoEliminado = productosServiceTest.obtenerProductoPorNombre(nombre);
        assertNull(productoEliminado, "El producto debería haber sido eliminada");


        assertEquals(null, productoEliminado);
    }
}