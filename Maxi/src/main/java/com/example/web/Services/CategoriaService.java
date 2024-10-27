package com.example.web.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Categoria;
import com.example.web.Dao.CategoriaDAO;
import com.example.web.Respositorios.CategoriaRepositorio;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaDAO categoriaDAO;
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;


    public List<Categoria> listarCategorias() {
        return (List<Categoria>) categoriaDAO.findAll();
    }

    public Categoria obtenerCategoriaPorNombre(String nombre) {
        return categoriaDAO.findByNombre(nombre);
    }

    public void agregarCategoria(String nombre, String descripcion) {
        categoriaRepositorio.insertarCat(nombre, descripcion);
    }

    public void actualizarCategoria(String nombre, String descripcion, int id) {
        categoriaRepositorio.actualizarCat(nombre, descripcion, id);
    }

    public void eliminarCategoria(int id) {
        categoriaDAO.deleteById(Long.valueOf(id));
    }
}
