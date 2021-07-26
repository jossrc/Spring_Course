package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
// import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SessionAttributes("usuario") // Indicamos que key-value se almacenará en una session
public class FormController {

    /*
    * Inyectando nuestra clase que contiene nuestras validaciones personalizadas
    * */
    @Autowired
    private UsuarioValidador validador;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // debemos usar addValidators para que use todos los tipos de  validaciones
        // si usamos el setValidators es solo cuando tenemos una clase exclusiva encargada de eso
        // ya que sino ignora los otros
        binder.addValidators(validador);
    }

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

        // Enviando al resultado
        model.addAttribute("usuario", usuario);

        return "resultado";
    }

    /*
    * Otra forma de trabajar con los formularios pero más corto
    * */
    @GetMapping("/form2")
    public String form2(Model model) {

        Usuario usuario = new Usuario();
        // Estableciendo valores por defecto
        usuario.setNombre("José");
        usuario.setApellido("Robles");
        // Este identificador no se está manejando, cuando se haga un post
        // volverá como null, tener cuidado con los valores por defecto
        usuario.setIdentificador("123.456.789-K");

        model.addAttribute("titulo", "Formulario 2 usuarios");
        // Se le envía al form2 para que sepa con que Clase trabajar
        model.addAttribute("usuario", usuario);
        return "form2";
    }

    @PostMapping("/form2")
    public String form2(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {

        // Aplicando las validaciones de la clase UsuarioValidador
        // Si usamos el InitBinder ya no es necesario hacer esto
        // validador.validate(usuario, result);

        model.addAttribute("titulo", "Resultado form 2");

        if ( result.hasErrors() ) {
            return "form2";
        }
        // Enviando al resultado
        model.addAttribute("usuario", usuario);

        // EL @SessionAttributes Permite que los valores establecidos inicialmente no desaparezcan
        // además si el valor cambia, actualiza su campo, muy útil para los id,
        // identificador, ya que estos por lo general no están en un formulario
        // También conserva los valores entre controladores, si queremos eso no debemos
        // limpiar la session

        // El status.setComplete limpia la session y libera recursos
        // recomendado después de utilizar el valor almacenado allí
        status.setComplete();

        return "resultado";
    }

}
