package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    @Autowired // busca la clase que implementa la interfaz
    @Qualifier("clienteDaoJPA") // Busca al Repository por su identificador (recomendado si muchas clases usan la misma interfaz)
    private IClienteDao clienteDAO;

    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteDAO.findAll());
        return "listar";
    }

}
