package com.example.web.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.Usuarios;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UsuarioService usuarioService;

    
    @Autowired
    HttpSession session;

    private Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public CustomUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuarios usuario = usuarioService.obtenerUsuarioPorCorreo(correo);
        System.out.println("Usuario no encontrado");

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        } 

        log.info("Esto es el id del usuario: {}", usuario.getIdUsuario());
        session.setAttribute("id", usuario.getIdUsuario());
        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getContraseña())
            .roles(usuario.getRol()) 
            .build();
    }
}