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
import java.sql.Date;

import lombok.Data;

@Data
@Entity
@Table(name = "SGI_PEDIDOPROVEEDOR_TB")
public class Pedido_proveedor implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="c_pedido")
    private int idPedido;
    
    @Column(name="c_usuario")
    private int IdUsuario;

    @Column(name="c_ruc_proveedor")
    private int idProveedor;

    @Column(name="fecha_solicitud")
    private Date fecha;   

    @Column(name="n_monto_total")
    private double total;   

    @Column(name="n_descuento")
    private int descuento;   

    @Column(name="n_impuesto")
    private int impuesto;   
}
