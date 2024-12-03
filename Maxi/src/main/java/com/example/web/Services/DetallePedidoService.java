package com.example.web.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Detalle_pedido;
import com.example.web.Dao.DetallePedidoDAO;
import com.example.web.Respositorios.DetallePedidoRepositorio;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoDAO detallePedidoDAO;

    @Autowired
    private ProductoService productosService;

    @Autowired
    DetallePedidoRepositorio detallePedidoRepositorio;

    public Iterable<Detalle_pedido> obtenerDetalle() {
        return detallePedidoDAO.findAll();
    }

    public List<Detalle_pedido> obtenerPedidoPorIdPedido(int id) {
        return detallePedidoDAO.findByidPedido(id);
    }

    public void agregarDetalle(Detalle_pedido detalle_pedido) {

        detallePedidoDAO.save(detalle_pedido);
        productosService.aumentarStocks(detalle_pedido.getIdProducto(), detalle_pedido.getCantidad());
    }

    public void eliminarDetalle(int id) {

        detallePedidoRepositorio.eliminar_detalle(id);

    }

}
