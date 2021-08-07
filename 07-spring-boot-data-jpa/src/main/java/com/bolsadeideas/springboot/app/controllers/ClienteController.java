package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("cliente")
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

        // podemos usar Model o Map

        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        model.put("titulo", "Formulario de Cliente");
        return "form";
    }

    @PostMapping(value = "/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {

        // El BindingResult nos permite saber si lo ingresado en el formulario es válido o no
        // Se necesita de la anotación @Valid en el atributo de tipo Cliente
        // Deben estar uno al lado del otro, el tercero debe ser el Model

        if (result.hasErrors()) {

            // No es necesario volver a enviar al cliente al formulario, ya que como se está
            // trabajando con el parámetro Cliente (al inicio) este lo envía por defecto
            // Sin embargo, esto funciona siempre y cuando el nombre (key) que se envía
            // por el GET (enviándolo) sea el mismo que la clase en lowercase. Cliente -> cliente
            // Pero si es distinto se requerirá usar la anotación @ModelAttribute y dentro
            // como parámetro se ingresará el nombre (key) como se le envía por el GET

            model.addAttribute("titulo", "Formulario de Clientes");
            return "form";
        }

        clienteDAO.save(cliente);
        status.setComplete();// Limpia el session
        return "redirect:listar";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

        Cliente cliente = null;

        if (id > 0) {
            cliente = clienteDAO.findOne(id);
        } else {
            return "redirect:listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }

    @GetMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {

        if (id > 0) {
            clienteDAO.delete(id);
        }

        return "redirect:/listar";
    }

}
