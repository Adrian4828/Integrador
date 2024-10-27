package com.example.web.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Proveedor;
import com.example.web.Dao.ProveedorDAO;
import com.example.web.Respositorios.ProveedorRepositorio;

@Service
public class ProveedoresService {

    @Autowired
    private ProveedorDAO proveedorDAO;

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    public Iterable<Proveedor> obtenerProveedores() {
        return proveedorDAO.findAll();
    }

    public Proveedor obtenerProveedorPorTelefono(int telefono){
        return proveedorDAO.findByTelefono(telefono);
    }

    public Proveedor obtenerProveedorPorNombre(String nombre){
        return proveedorDAO.findByNombre(nombre);
    }

    public void agregarProveedor(String nombre, String direccion, int telefono, String email) {

        proveedorRepositorio.insertarProv(nombre, direccion, telefono, email);

    }

    public void actualizarProveedor(String nombre, String direccion, int telefono, String email, int id_proveedor) {

        proveedorRepositorio.actualizarProv( nombre,  direccion,  telefono,  email,  id_proveedor);

    }

    public void eliminarProveedor(int id) {
        proveedorDAO.deleteById(Long.valueOf(id));
    }
}