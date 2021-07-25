package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("titulo", "Formulario usuarios");
        return "form";
    }

    /**
     * Cuando se trabaja con un post desde un formulario
     * podemos enviarle todos los datos de golpe a la clase
     * que lo representa (solo funciona con una clase). Los name
     * de los campos de un formulario deben ser los mismos que
     * las propiedades de la clase para que funcione, además
     * debe tener getters y setters.
     * En caso que no lo necesitáramos podemos usar un @RequestParam
     * Por ejemplo @RequestParam(name="txtUsername") String username
     * en caso que el name tenga otra descripción
     * o @RequestParam String email, si son los mismos
     */
    @PostMapping("/form")
    public String procesar(Usuario usuario, Model model) {

        model.addAttribute("titulo", "Resultado form");
        model.addAttribute("usuario", usuario);

        return "resultado";
    }

}
