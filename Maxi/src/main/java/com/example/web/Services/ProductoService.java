package com.example.web.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Categoria;
import com.example.domain.Producto;
import com.example.domain.Proveedor;
import com.example.web.Dao.CategoriaDAO;
import com.example.web.Dao.ProductosDAO;
import com.example.web.Dao.ProveedorDAO;
import com.example.web.Respositorios.ProductoRepositorio;

@Service
public class ProductoService {

    @Autowired
    private ProductosDAO productosDAO;

    @Autowired
    private CategoriaDAO categoriaDAO;

    @Autowired
    private ProveedorDAO proveedorDAO;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    public Iterable<Producto> obtenerProductos() {
        return productosDAO.findAll();
    }

    public Iterable<Categoria> obtenerCategorias() {
        return categoriaDAO.findAll();
    }

    public Iterable<Proveedor> obtenerProveedores() {
        return proveedorDAO.findAll();
    }

    public Producto obtenerProductoPorNombre(String nombre) {
        return productosDAO.findByNombre(nombre);
    }

    public void agregarProducto(String nombreCategoria, String nombreProveedor, String descripcion, String nombre,
            double precio, int stock) {
        Categoria categoria = categoriaDAO.findByNombre(nombreCategoria);
        Proveedor proveedor = proveedorDAO.findByNombre(nombreProveedor);

        productoRepositorio.insertarProd(categoria.getIdCategoria(), proveedor.getIdProveedor(), descripcion, nombre,
                precio, stock);
    }

    public void actualizarProducto(String nombre_categoria, String nombre_proveedor, String descripcion, String nombre, double precio, int stock, int id_producto) {

        Categoria categoria = categoriaDAO.findByNombre(nombre_categoria);
        Proveedor proveedor = proveedorDAO.findByNombre(nombre_proveedor);
        
        productoRepositorio.actualizarProd(categoria.getIdCategoria(), proveedor.getIdProveedor(), descripcion, nombre,
        precio, stock, id_producto);

    }

    public void eliminarProducto(int id) {
        productosDAO.deleteById(Long.valueOf(id));
    }
}
