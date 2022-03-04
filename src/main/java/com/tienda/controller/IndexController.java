
package com.tienda.controller;

import com.tienda.dao.ClienteDao;
import com.tienda.model.Cliente;
import com.tienda.service.ClienteService;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class IndexController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/")
    public String inicio(Model model) {
        log.info("Se muestra la página index de templates");
        var clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);   
        return "index";
    }
    
    @GetMapping("/nuevoCliente")
    public String nuevoCliente(Cliente cliente) {    
        return "modificarCliente";     
    }
    
    @PostMapping("/guardarCliente")
    public String guardarCliente(Cliente cliente){    
        clienteService.save(cliente);
        return "redirect:/"; 
    }
    
    @GetMapping("/modificarCliente/{idCliente}")
    public String modificarCliente(Cliente cliente, Model model) {
        cliente = clienteService.getCliente(cliente);
        model.addAttribute("cliente",cliente);
        return "modificarCliente";
    }
        
    @GetMapping("/eliminarCliente/{idCliente}")
    public String eliminarCliente(Cliente cliente) {
        clienteService.delete(cliente);
        return "redirect:/";    
    }
}
