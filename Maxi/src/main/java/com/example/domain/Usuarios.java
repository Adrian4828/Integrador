package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "sgi_usuario_tb")
public class Usuarios implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_usuario")
    private int idUsuario;
    
    @Column(name ="x_nombre")
    private String nombre;
    @Column(name ="x_rol")
    private String rol;
    @Column(name = "x_email")
    private String email;
    @Column(name = "x_contraseña")
    private String contraseña;
}
