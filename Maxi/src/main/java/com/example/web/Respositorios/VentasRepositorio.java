package com.example.web.Respositorios;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class VentasRepositorio{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void registrar_venta(int id_usuario, Date fecha, double total) {
        String sql = "INSERT INTO sgi_ventas_tb (c_usuario, f_venta, n_total_venta) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id_usuario, fecha, total);
    }
}