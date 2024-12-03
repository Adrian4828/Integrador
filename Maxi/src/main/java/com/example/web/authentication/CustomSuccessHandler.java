package com.example.web.authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.web.Services.UsuarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        HttpSession session = request.getSession();

        session.setAttribute("nombre", authentication.getName());
        session.setAttribute("rol", authentication.getAuthorities().iterator().next().getAuthority());
        session.setAttribute("id", usuarioService.obtenerUsuarioPorCorreo(authentication.getName()).getIdUsuario());

        if (role.equals("X_ROLE_Administrador")) {
            response.sendRedirect("/categorias");
            response.sendRedirect("/dashboard");
        } else {
            response.sendRedirect("/");
        }
    }
}