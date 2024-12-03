/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.web.Respositorios;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleRepositorio {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void registrar_detalle(int id_ventas, int id_producto, int cantidad_producto) {
        String sql = "INSERT INTO sgi_detalleventas_tb (c_ventas, c_producto, n_cantidad_producto) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, id_ventas, id_producto, cantidad_producto);
    }

    public void eliminar_detalle(int id_ventas) {
        String sql = "DELETE FROM sgi_detalleventas_tb WHERE c_ventas = ?";
        jdbcTemplate.update(sql, id_ventas);
    }

    public List<Map<String, Object>> top5(){
        String sql = "SELECT c_producto, n_cantidad_producto, c_ventas FROM sgi_detalleventas_tb ORDER BY n_cantidad_producto DESC LIMIT 5";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> top5porFecha(String fecha){
        String sql = "SELECT dv.c_producto, dv.n_cantidad_producto, dv.c_ventas \n" + //
                        "FROM sgi_detalleventas_tb dv\n" + //
                        "JOIN sgi_ventas_tb v ON dv.c_ventas = v.c_ventas\n" + //
                        "WHERE v.f_venta = ?\n" + //
                        "ORDER BY dv.n_cantidad_producto DESC\n" + //
                        "LIMIT 5;";
        return jdbcTemplate.queryForList(sql, fecha);
    }


}
