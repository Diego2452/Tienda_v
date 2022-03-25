
package com.tienda.controller;

import com.tienda.dao.CategoriaDao;
import com.tienda.model.Categoria;
import com.tienda.service.CategoriaService;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    private Object articuloService;
    
    @GetMapping("/articulo/listado")
    public String inicio(Model model){
        var articulos = articuloService.getArticulos(true);
        model.addAttribute("articulos", articulos);
        return "/articulo/listado";
    }
        
    @GetMapping("/categoria/nuevo")
    public String nuevoCategoria(Categoria categoria) {    
        return "/categoria/modificar";     
    }
    
    @PostMapping("/categoria/guardar")
    public String guardarCategoria(Categoria categoria){    
        categoriaService.save(categoria);
        return "redirect:/categoria/listado"; 
    }
    
    @GetMapping("/categoria/modificar/{idCategoria}")
    public String modificarCategoria(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria",categoria);
        return "/categoria/modificar";
    }
        
    @GetMapping("/categoria/eliminar/{idCategoria}")
    public String eliminarCategoria(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/categoria/listado";    
    }
}
