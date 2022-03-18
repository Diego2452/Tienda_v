package com.tienda.service;

import com.tienda.dao.CategoriaDao;
import com.tienda.model.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Se utiliza la siguiente anotación para que esta clase
//Pueda utilizarse dentro de un "controller", sin problema

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    @Autowired
    private CategoriaDao categoriaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activo) {
        var lista = (List<Categoria>) categoriaDao.findAll();
        if (activo) {
            // Se remueve de la lista las articulos que estan no activo
            lista.removeIf( e -> !e.isActivo());
        }
        return lista;
        
    }    
    
    @Override
    @Transactional
    public void save (Categoria categoria) {
        categoriaDao.save(categoria);
    }
        
    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }    
        
    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }
}