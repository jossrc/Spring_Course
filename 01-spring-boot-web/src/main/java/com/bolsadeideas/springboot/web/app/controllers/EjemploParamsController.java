package com.bolsadeideas.springboot.web.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/params")
public class EjemploParamsController {

    @GetMapping("/")
    public String index() {
        return "params/index";
    }

    /**
     * Al usar el RequestParam podremos enviar parámetros
     * en caso de necesitarlo, estos son del tipo GET
     * por defecto es required (true), en caso que no se
     * le mande obtendremos error, pero si lo desactivamos
     * estaremos esperando un valor o un null (defecto)
     *
     * @param texto Parámetro GET ?texto=""
     * @param model Valor a enviar a la vista
     * @return Página ver
     */
    @GetMapping("/string")
    public String param(@RequestParam(name = "texto", required = false, defaultValue = "Algún texto") String texto, Model model) {
        // Usamos el query param llamado texto
        model.addAttribute("resultado", "El texto enviado es: ".concat(texto));

        // Indicamos que dentro de template debe existir una carpeta llamado
        // params, y dentro debe estar la página ver
        return "params/ver";
    }

    /**
     * Se puede tener varios métodos con el mismo nombre,
     * sin embargo, los parámetros tienen que ser distintos
     */
    @GetMapping("/mix-params")
    public String param(@RequestParam String saludo, @RequestParam Integer numero, Model model) {
        model.addAttribute("resultado", "El saludo es: '" + saludo + "' y el número es '" + numero + "'");
        return "params/ver";
    }

    /**
     * Otra forma de recibir los parámetros es por la request
     * usando el HttpServletServlet - NO RECOMENDADO
     */
    @GetMapping("/mix-params-request")
    public String param(HttpServletRequest request, Model model) {

        String saludo = request.getParameter("saludo");
        Integer numero = null;

        try {
            numero = Integer.parseInt(request.getParameter("numero"));
        } catch (NumberFormatException e) {
            numero = 0;
        }

        model.addAttribute("resultado", "El saludo es: '" + saludo + "' y el número es '" + numero + "'");

        return "params/ver";
    }

}
