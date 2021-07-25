package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
// import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("usuario", new Usuario());
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
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {
        // La anotación @Valid permite aplicar las reglas implementadas en el objeto
        model.addAttribute("titulo", "Resultado form");
        // Para saber si se cumplen las reglas se debe usar el BindingResult, el cual
        // debe estar después de nuestro objeto (segundo)
        if ( result.hasErrors() ) {
            Map<String, String> errores = new HashMap<>();
            // Recorremos los errores encontrados y lo agregamos en un map
            result.getFieldErrors().forEach(err -> {
                errores.put(
                   err.getField(),
                   "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });
            model.addAttribute("error", errores);
            // El objeto Usuario se envía al form automáticamente, también se
            // puede hacer manualmente, la diferencia con el otro es que este
            // envía la Clase como 'camelCase' más no la instancia
            // Si no se quiere hacer de esa manera se puede usar el @ModelAttribute() para
            // cambiar el nombre y no tome el de la Clase, sin embargo se deberá
            // cambiar en todos lados incluso en el model
            return "form";
        }
        model.addAttribute("usuario", usuario);

        return "resultado";
    }

}
