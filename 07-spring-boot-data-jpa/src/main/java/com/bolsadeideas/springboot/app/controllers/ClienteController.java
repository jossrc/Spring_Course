package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

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

    @GetMapping("/form")
    public String crear(Map<String, Object> model){
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "form";
    }

    @PostMapping(value = "/form")
    public String guardar(Cliente cliente) {
        clienteDAO.save(cliente);
        return "redirect:listar";
    }

}
