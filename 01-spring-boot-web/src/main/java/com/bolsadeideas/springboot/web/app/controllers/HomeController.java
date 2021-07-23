package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        /*
        * Si queremos que al llegar a una página este nos
        * redirija a otra, pero cambiando la url se debe
        * utilizar el redirect.
        * Pero si queremos mostrar la página pero sin
        * cambiar la URL se debe usar el forward.
        * Este último es muy recomendado.
        *
        * */

        return "redirect:/app/index";
    }
}
