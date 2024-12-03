package com.example.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/", "/acceder").permitAll()
                        .requestMatchers("/categoria/**", "/actualizar_cat/**", "/agregar_cat/**", "/eliminar_cat/**").hasRole("Administrador")
                        .requestMatchers("/productos/**", "/agregar_prod/**", "/actualizar_prod/**", "/eliminar_prod/**").hasRole("Administrador")
                        .requestMatchers("/proveedores/**", "/agregar_prov/**", "/actualizar_prov/**", "/eliminar_prov/**").hasRole("Administrador")
                        .requestMatchers("/usuarios/**", "/agregar_usu/**", "/actualizar_usu/**", "/eliminar_usu/**").hasRole("Administrador")
                        .requestMatchers("/reporte-pdf/**", "/reporteUsuarios-pdf/**", "/reporteCategorias-pdf/**", "/reporteProveedores-pdf/**").hasRole("Administrador")
                        .requestMatchers("/generarExcel/**", "/excelProv/**", "/excelCat/**", "/excelUsuarios/**").hasRole("Administrador")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/acceder", true)
                        .failureUrl("/?error=true"))
                .logout(logout -> logout
                        .logoutUrl("/cerrar")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .permitAll());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
