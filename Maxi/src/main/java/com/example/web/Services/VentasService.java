package com.example.web.Services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Categoria;
import com.example.domain.Producto;
import com.example.domain.Usuarios;
import com.example.domain.Ventas;
import com.example.web.Dao.CategoriaDAO;
import com.example.web.Dao.ProductosDAO;
import com.example.web.Dao.UsuariosDAO;
import com.example.web.Dao.VentasDAO;
import com.example.web.Respositorios.VentasRepositorio;


@Service
public class VentasService {

    @Autowired
    private VentasDAO ventasDAO;

    @Autowired
    private VentasRepositorio ventasRepositorio;

    @Autowired
    private ProductosDAO productoDAO;

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Autowired
    private UsuariosDAO usuariosDAO;


    public List<Ventas> listarVentas() {
        return (List<Ventas>) ventasDAO.findAll();
    }

    public List<Categoria> listarCategorias() {
        return (List<Categoria>) categoriaDAO.findAll();
    }

    public List<Usuarios> listarUsuarios() {
        return (List<Usuarios>) usuariosDAO.findAll();
    }

    public List<Producto> listarProductos() {
        return (List<Producto>) productoDAO.findAll();
    }

    public Ventas obtenerVentasPorNombre(double total) {
        return ventasDAO.findByTotal(total);
    }

    public Optional<Ventas> obtenerVentasPorId(int id) {
        return ventasDAO.findById(Long.valueOf(id));
    }

    public void agregarVenta(int id_usuario, Date fecha, double total) {
        ventasRepositorio.registrar_venta(id_usuario, fecha, total);
    }

    public void eliminarVenta(int id) {
        ventasDAO.deleteById(Long.valueOf(id));
    }
}