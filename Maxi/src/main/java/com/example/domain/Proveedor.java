/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
@Table(name = "SGI_PROVEEDOR_TB")
public class Proveedor implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="c_ruc_proveedor")
    private int idProveedor;
    
    @Column(name="x_nombre")
    private String nombre;

    @Column(name="x_direccion")
    private String direccion;

    @Column(name="n_telefono")
    private int telefono;

    @Column(name="x_email")
    private String email;
    
}
