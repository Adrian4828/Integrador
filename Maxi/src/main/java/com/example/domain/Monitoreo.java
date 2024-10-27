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
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "SGI_MONITOREO_TB")
public class Monitoreo implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="c_monitoreo")
    private int idMonitoreo;
    
    @Column(name="c_usuario")
    private int id_Usuario;

    @Column(name="fecha_apertura")
    private LocalDateTime fechaApertura;

    @Column(name="fecha_cierre")
    private LocalDateTime fechaCierre;

    

    
    
}
