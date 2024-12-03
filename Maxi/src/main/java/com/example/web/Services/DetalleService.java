package com.example.web.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.DetalleVenta;
import com.example.web.Dao.DetalleVentaDAO;
import com.example.web.Respositorios.DetalleRepositorio;


@Service
public class DetalleService {
    @Autowired
    private DetalleVentaDAO detalleDAO;

    @Autowired
    private ProductoService productosService;

    @Autowired
    DetalleRepositorio detalleRepositorio;

    public Iterable<DetalleVenta> obtenerDetalle() {
        return detalleDAO.findAll();
    }

    public List<DetalleVenta> obtenerVentasPorIdVenta(int id) {
        return detalleDAO.findByidVentas(id);
    }

    public void agregarDetalle(DetalleVenta detalle_ventas) {

        detalleDAO.save(detalle_ventas);

        productosService.actualizarStocks(detalle_ventas.getIdProducto(), detalle_ventas.getCantidadProductos());
    }

    public void actualizarDetalle(DetalleVenta detalle_ventas) {

        detalleDAO.save(detalle_ventas);

    }

    public void eliminarDetalle(int id) {

        detalleRepositorio.eliminar_detalle(id);

    }

    public void obtenerTop5(){
        detalleRepositorio.top5();
    }
}
