package com.tienda.service;

import com.tienda.dao.ClienteDao;
import com.tienda.model.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Se utiliza la siguiente anotación para que esta clase
//Pueda utilizarse dentro de un "controller", sin problema

@Service
public class ClienteServiceImpl implements ClienteService{
    
    @Autowired
    private ClienteDao clienteDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }    
    
    @Override
    @Transactional
    public void save (Cliente cliente) {
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
