package com.example.web.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.DetalleVenta;


public interface DetalleVentaDAO extends CrudRepository<DetalleVenta, Long>{
        List<DetalleVenta> findByidVentas(int id);

}
