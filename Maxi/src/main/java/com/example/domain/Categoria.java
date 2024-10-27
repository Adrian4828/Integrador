
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
@Table(name = "SGI_CATEGORIA_TB")
public class Categoria implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id_categoria")
    private int idCategoria;
    
    @Column(name ="x_nombre")
    private String nombre;
    @Column(name ="x_descripcion")
    private String descripcion;
    
}
