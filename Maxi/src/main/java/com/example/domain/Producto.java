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
@Table(name = "SGI_PRODUCTO_TB")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_producto")
    private int idProducto;
    @Column(name = "c_id_categoria")
    private int idCategoria;
    @Column(name = "c_ruc_proveedor")
    private int rucProveedor;
    @Column(name = "x_descripcion_prod")
    private String descripcion;
    @Column(name = "x_nombre_prod")
    private String nombre;
    @Column(name = "n_precio_venta")
    private double precio;
    @Column(name = "n_stock")
    private int stock;

}
