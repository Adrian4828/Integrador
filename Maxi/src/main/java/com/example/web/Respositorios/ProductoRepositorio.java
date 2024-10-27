package com.example.web.Respositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class ProductoRepositorio {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void insertarProd(int id_categoria, int ruc_proveedor, String descripcion, String nombre, double precio, int stock) {
        String sql = "INSERT INTO sgi_producto_tb (c_id_categoria, c_ruc_proveedor, x_descripcion_prod, x_nombre_prod, n_precio_venta, n_stock) VALUES (?, ?,?,?,?,?)";
        jdbcTemplate.update(sql, id_categoria, ruc_proveedor, descripcion, nombre, precio, stock);
    }

    public void eliminarProd(int id_producto){
        String sql = "DELETE FROM sgi_producto_tb WHERE c_producto = ?";
        jdbcTemplate.update(sql, id_producto);
    }


    public void actualizarProd(int id_categoria, int ruc_proveedor, String descripcion, String nombre, double precio, int stock, int id_producto) {
        String sql = "UPDATE sgi_producto_tb SET c_id_categoria = ?, c_ruc_proveedor = ?, x_descripcion_prod = ?, x_nombre_prod = ?, n_precio_venta = ?, n_stock = ?  WHERE c_producto = ?";
        jdbcTemplate.update(sql, id_categoria, ruc_proveedor, descripcion, nombre, precio, stock, id_producto);
    }
}
