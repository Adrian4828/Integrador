package com.example.web.Services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Pedido_proveedor;
import com.example.domain.Producto;
import com.example.domain.Proveedor;
import com.example.domain.Usuarios;
import com.example.web.Dao.PedidoDAO;
import com.example.web.Dao.ProductosDAO;
import com.example.web.Dao.ProveedorDAO;
import com.example.web.Dao.UsuariosDAO;
import com.example.web.Respositorios.PedidoRepositorio;

@Service
public class PedidoService {

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProductosDAO productoDAO;

    @Autowired
    private ProveedorDAO proveedorDAO;

    @Autowired
    private UsuariosDAO usuariosDAO;

    public List<Pedido_proveedor> listarPedido() {
        return (List<Pedido_proveedor>) pedidoDAO.findAll();
    }

    public List<Proveedor> listarProveedores() {
        return (List<Proveedor>) proveedorDAO.findAll();
    }

    public List<Usuarios> listarUsuarios() {
        return (List<Usuarios>) usuariosDAO.findAll();
    }

    public List<Producto> listarProductos() {
        return (List<Producto>) productoDAO.findAll();
    }

    public Optional<Pedido_proveedor> obtenerPedidoPorId(int id) {
        return pedidoDAO.findById(Long.valueOf(id));
    }

    public void agregarPedido(int id_usuario, int id_proveedor, Date fecha, double total, int descuento, int impuesto) {
        pedidoRepositorio.registrar_pedido(id_usuario, id_proveedor, fecha, total, descuento, impuesto);
    }

    public void eliminarPedido(int id) {
        pedidoDAO.deleteById(Long.valueOf(id));
    }

}