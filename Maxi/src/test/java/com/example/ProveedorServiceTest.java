package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Proveedor;
import com.example.web.Services.ProveedoresService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProveedorServiceTest {

    @Autowired
    private ProveedoresService proveedoresService;

    @Test
    void testAgregarProveedor() {
        String nombre = "Carlos";
        String direccion = "Av San Martin";
        int telefono = 956321234;
        String email = "carl24@gmail.com";
        proveedoresService.agregarProveedor( nombre,  direccion,  telefono,  email);
        assertEquals(proveedoresService.obtenerProveedorPorTelefono(telefono),
                proveedoresService.obtenerProveedorPorTelefono(telefono));
    }

    @Test
    void testEliminar() {
        
        int telefono = 956321234;

        Proveedor proveedor = proveedoresService.obtenerProveedorPorTelefono(telefono);
        assertNotNull(proveedor, "El proveedor debe existir antes de eliminarlo");

        proveedoresService.eliminarProveedor(proveedor.getIdProveedor());

        Proveedor proveedorEliminado = proveedoresService.obtenerProveedorPorTelefono(telefono);
        assertNull(proveedorEliminado, "El proveedor debería haber sido eliminada");


        assertEquals(null, proveedorEliminado);
    }
}
