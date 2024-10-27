package com.example.web.Respositorios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositorio {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insertarUsu(String rol, String email, String nombre, String contraseña) {
        String sql = "INSERT INTO sgi_usuario_tb (x_rol, x_email, x_nombre, x_contraseña) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, rol, email, nombre, contraseña);
    }

    public void eliminarUsu(int id_usuario){
        String sql = "DELETE FROM sgi_usuario_tb WHERE c_usuario = ?";
        jdbcTemplate.update(sql, id_usuario);
    }

    public void actualizarUsu(String rol, String email, String nombre, String contraseña, int id_usuario) {
        String sql = "UPDATE sgi_usuario_tb SET x_rol = ?, x_email = ?, x_nombre = ?, x_contraseña = ? WHERE c_usuario = ?";
        jdbcTemplate.update(sql, rol, email, nombre, contraseña, id_usuario);
    }
}
