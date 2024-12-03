package com.example.web.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.Detalle_pedido;

public interface DetallePedidoDAO extends CrudRepository<Detalle_pedido, Long> {
    List<Detalle_pedido> findByidPedido(int id);
}
