package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Categoria;
import com.example.domain.Reporte;
import com.example.web.Services.CategoriaService;

@SpringBootTest
public class ReporteServiceTest {

    @Autowired
    private CategoriaService categoriaService;

    @Test
    void testGenerar() {
        Reporte reporte = new Reporte();
        List<Categoria> categorias = categoriaService.listarCategorias();
        reporte.crearReporteCat(categorias);

        assertEquals("Archivo creado", reporte);
    }
}
