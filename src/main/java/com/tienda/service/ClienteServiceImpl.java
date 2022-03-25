package com.tienda.service;

import com.tienda.dao.CategoriaDao;
import com.tienda.dao.ClienteDao;
import com.tienda.model.Categoria;
import com.tienda.model.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    private ClienteDao clienteDao;
    
    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        Categoria categoria = cliente.getCategoria();//Obtengo el objeto crédito del cliente a salvar.
        categoria = categoriaDao.save(categoria);//Salvo el objeto crédito en la tabla crédito. Se reasigna el idCategoria.
        cliente.setCategoria(categoria);
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }
    
}
