package com.example.web.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Proveedor;

@Repository
public interface ProveedorDAO extends CrudRepository<Proveedor, Long>{
    Proveedor findByNombre(String nombre);
    Proveedor findByTelefono(int telefono);
}
