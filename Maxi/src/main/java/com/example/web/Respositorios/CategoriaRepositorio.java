package com.example.web.Respositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaRepositorio {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarCat(String nombre, String descripcion) {
        String sql = "INSERT INTO sgi_categoria_tb (x_nombre, x_descripcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, nombre, descripcion);
    }

    public void eliminarCat(int id_categoria){
        String sql = "DELETE FROM sgi_categoria_tb WHERE c_id_categoria = ?";
        jdbcTemplate.update(sql, id_categoria);
    }

    public void actualizarCat(String nombre, String descripcion, int id_categoria) {
        String sql = "UPDATE sgi_categoria_tb SET x_nombre = ?, x_descripcion = ? WHERE c_id_categoria = ?";
        jdbcTemplate.update(sql, nombre, descripcion, id_categoria);
    }
}
