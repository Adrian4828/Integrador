package com.example.web.Respositorios;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PedidoRepositorio{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void registrar_pedido(int id_usuario,int id_proveedor, Date fecha, double total, int descuento, int impuesto) {
        String sql = "INSERT INTO sgi_pedidoproveedor_tb (c_usuario, c_ruc_proveedor, fecha_solicitud, n_monto_total, n_descuento, n_impuesto) VALUES (?, ?, ?, ?,?,?)";
        jdbcTemplate.update(sql, id_usuario,id_proveedor, fecha, total, descuento, impuesto);
    }
}