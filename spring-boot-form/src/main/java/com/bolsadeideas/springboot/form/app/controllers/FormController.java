package com.bolsadeideas.springboot.form.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("titulo", "Formulario usuarios");
        return "form";
    }

    @PostMapping("/form")
    public String procesar(Model model,
           @RequestParam(name="txtUsername") String username,
           @RequestParam(name="txtEmail") String email,
           @RequestParam(name="txtPassword") String password) {

        model.addAttribute("titulo", "Resultado form");
        model.addAttribute("username", username);
        model.addAttribute("password", email);
        model.addAttribute("email", password);

        return "resultado";
    }

}
