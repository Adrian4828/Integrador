package com.example.web.Dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Producto;

/**
 *
 * @author mz245
 */
@Repository
public interface ProductosDAO extends CrudRepository<Producto, Long>{
    Producto findByNombre(String nombre);
}
