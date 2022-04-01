package com.tienda.service;

import com.tienda.dao.ArticuloDao;
import com.tienda.model.Articulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Se utiliza la siguiente anotación para que esta clase
//Pueda utilizarse dentro de un "controller", sin problema

@Service
public class ArticuloServiceImpl implements ArticuloService{
    
    @Autowired
    private ArticuloDao articuloDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Articulo> getArticulos(boolean activos) {
        var lista = (List<Articulo>) articuloDao.findAll();
        if (activos) {
            // Se remueve de la lista las articulos que estan no activo
            lista.removeIf( e -> !e.isActivo());
        }
        return lista;
        
    }    
    
    @Override
    @Transactional
    public void save (Articulo articulo) {
        articuloDao.save(articulo);
    }
        
    @Override
    @Transactional
    public void delete(Articulo articulo) {
        articuloDao.delete(articulo);
    }    
        
    @Override
    @Transactional(readOnly = true)
    public Articulo getArticulo(Articulo articulo) {
        return articuloDao.findById(articulo.getIdArticulo()).orElse(null);
    }
}