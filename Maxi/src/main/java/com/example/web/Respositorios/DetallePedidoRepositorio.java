package com.example.web.Respositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class DetallePedidoRepositorio {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void registrar_detalle(int id_pedido, int id_producto, int cantidad_producto) {
        String sql = "INSERT INTO sgi_detallepedido_tb (c_pedido, c_producto, n_cantidad) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id_pedido, id_producto, cantidad_producto);
    }

    public void eliminar_detalle(int id_pedido) {
        String sql = "DELETE FROM sgi_detallepedido_tb WHERE c_pedido = ?";
        jdbcTemplate.update(sql, id_pedido);
    }


}