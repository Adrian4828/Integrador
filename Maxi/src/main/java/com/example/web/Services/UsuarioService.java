package com.example.web.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Usuarios;
import com.example.web.Dao.UsuariosDAO;
import com.example.web.Respositorios.UsuarioRepositorio;
import com.password4j.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuariosDAO usuariosDAO;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Iterable<Usuarios> obtenerUsuarios() {
        return usuariosDAO.findAll();
    }

    public Usuarios obtenerUsuarioPorEmailContraseña(String x_email, String contraseña) {
        
        return usuariosDAO.findByEmailAndContraseña(x_email, contraseña);
    }

    public Usuarios obtenerUsuarioPorEmailContraseñaEncriptada(String x_email, String contraseña){
        Usuarios usuario = usuariosDAO.findByEmail(x_email);
        boolean verificar = Password.check(contraseña, usuario.getContraseña()).addSalt("@#").withPBKDF2();

        if(verificar){
            return usuario;
        } else {
            return null;
        }
    }

    public void agregarUsuario(String rol,
            String email,
            String nombre,
            String contraseña) {

        Hash hash = Password.hash(contraseña).addSalt("@#").withPBKDF2();

        usuarioRepositorio.insertarUsu(rol, email, nombre, hash.getResult());

    }

    public void actualizarUsuario(String rol,
            String email,
            String nombre,
            String contraseña, int id_usuario) {

        Hash hash = Password.hash(contraseña).addSalt("@#").withPBKDF2();

        usuarioRepositorio.actualizarUsu(rol, email, nombre, hash.getResult(), id_usuario);

    }

    public void eliminarUsuario(int id) {
        usuariosDAO.deleteById(Long.valueOf(id));
    }
}
