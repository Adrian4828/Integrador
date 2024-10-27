/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.web.Dao;

import com.example.domain.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosDAO extends CrudRepository<Usuarios, Long>{
    Usuarios findByEmailAndContraseña(String x_email, String x_contraseña);
    Usuarios findByEmail(String x_email);
}
