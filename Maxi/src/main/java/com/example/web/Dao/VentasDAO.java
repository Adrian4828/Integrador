package com.example.web.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Ventas;


public interface VentasDAO extends CrudRepository<Ventas, Long> {
    Ventas findByTotal(double total);
}
