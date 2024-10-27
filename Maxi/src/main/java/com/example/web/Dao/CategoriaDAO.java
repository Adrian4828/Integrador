package com.example.web.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Categoria;

@Repository
public interface CategoriaDAO extends CrudRepository<Categoria, Long>{
    Categoria findByNombre(String nombre);
}
