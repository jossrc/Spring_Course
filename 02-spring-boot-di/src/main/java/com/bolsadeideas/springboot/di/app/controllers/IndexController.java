package com.bolsadeideas.springboot.di.app.controllers;

import com.bolsadeideas.springboot.di.app.models.service.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // Permite indicar con quién trabajar en caso de tener muchos servicios
    @Autowired
    @Qualifier("miServicioSimple")
    private IServicio servicio;

    /*
     * Para inyectar nuestro servicio usamos
     * la anotación Autowired, se recomienda usarlo en
     * un setter, o en el constructor, también se puede
     * en el atributo pero no se recomienda, en caso de tener muchos se usa
     * en el atributo para que acompañe al @Qualifier
     * */
    public IndexController(IServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping({"/index", "/", ""})
    public String index(Model model) {
        model.addAttribute("objeto", servicio.operacion());

        return "index";
    }

    public void setServicio(IServicio servicio) {
        this.servicio = servicio;
    }
}
