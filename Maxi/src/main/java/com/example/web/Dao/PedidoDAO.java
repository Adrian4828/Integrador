package com.example.web.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Pedido_proveedor;
public interface PedidoDAO extends CrudRepository<Pedido_proveedor, Long> {
    
}
