package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variables")
public class EjemploVariableRutaController {

    @GetMapping({"/", ""})
    public String index(Model model) {
        model.addAttribute("titulo", "Enviar Parámetros de la ruta (@PathVariable)");

        return "variables/index";
    }

    /**
     * Si queremos usar variables como parámetros, se
     * tiene que utilizar la Anotación @PathVariable.
     * La ruta debe estar junto con el nombre de la variable
     * pero este debe encontrarse entre llaves
     */
    @GetMapping("/string/{texto}")
    public String variables(@PathVariable String texto, Model model) {
        model.addAttribute("titulo", "Recibir parámetros de la ruta (@PathVariable)");
        model.addAttribute("resultado", "El texto enviado en la ruta es: " + texto);

        return "variables/ver";
    }

    /**
     * También podemos enviar más de una variable, tan solo se separa con
     * un slash
     */
    @GetMapping("/string/{texto}/{numero}")
    public String variables(@PathVariable String texto, @PathVariable Integer numero , Model model) {
        model.addAttribute("titulo", "Recibir parámetros de la ruta (@PathVariable)");
        model.addAttribute("resultado", "El texto enviado en la ruta es: " + texto + " y el número enviado en el path es: " + numero);

        return "variables/ver";
    }
}
