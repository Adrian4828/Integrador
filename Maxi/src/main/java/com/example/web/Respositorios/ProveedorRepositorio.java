package com.example.web.Respositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProveedorRepositorio {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarProv(String nombre, String direccion, int telefono, String email) {
        String sql = "INSERT INTO sgi_proveedor_tb (x_nombre, x_direccion, n_telefono, x_email) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, nombre, direccion, telefono, email);
    }

    public void eliminarProv(int id_proveedor){
        String sql = "DELETE FROM sgi_proveedor_tb WHERE c_ruc_proveedor = ?";
        jdbcTemplate.update(sql, id_proveedor);
    }

    public void actualizarProv(String nombre, String direccion, int telefono, String email, int id_proveedor) {
        String sql = "UPDATE sgi_proveedor_tb SET x_nombre = ?, x_direccion = ?, n_telefono = ?, x_email = ? WHERE c_ruc_proveedor = ?";
        jdbcTemplate.update(sql, nombre, direccion, telefono, email, id_proveedor);
    }
    
}