package com.bolsadeideas.springboot.web.app.controllers;

import com.bolsadeideas.springboot.web.app.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/app") // Establecemos una ruta base (padre) muy usado para api o crud
public class IndexController {

    /*
    * Con Spring se puede inyectar valores desde el
    * application.properties a un atributo, pero para
    * usarlo es necesario usar la Anotación @Value
    * y como parámetro usando el ${} va el key
    * que contiene el valor deseado. Sin embargo no se recomienda
    * hacerlo desde allí, es mejor crear otro archivo
    * ya que el principal es para datos sensibles.
    * Para inyectar valores desde otro property, se debe
    * crear una clase Config en el paquete principal del proyecto.
    * */

    @Value("${texto.indexcontroller.index.titulo}")
    private String textoIndex;

    @Value("${texto.indexcontroller.perfil.titulo}")
    private String textoPerfil;

    @Value("${texto.indexcontroller.listar.titulo}")
    private String textoListar;


    /**
     * Método que retorna una página. Esta página
     * debe encontrarse dentro de la carpeta templates
     *
     * @return Plantilla cuyo nombre es index
     */
    @GetMapping({"/index", "/", "", "/home"})
    public String index(Model model) {
        /*
         También podemos usar parámetros para enviar y recibir
         Usando el `Model` trabajaremos con datos del tipo llave-valor
         En ves de Model se puede usar el ModelMap que tiene los mismo métodos
         */
        model.addAttribute("titulo", textoIndex);

        return "index";
    }

    /**
     * Al igual que el Model, existe otra forma de enviar valores,
     * y este es usando el `ModelAndView`, sin embargo, a este
     * se le debe establecer el nombre de la vista ya que también
     * lo retornará
     *
     * @param mv Modelo y Vista
     * @return La Vista llamada saludo
     */
    @GetMapping("/saludo")
    public ModelAndView saludos(ModelAndView mv) {
        // Establecemos la clave y el valor
        mv.addObject("saludar", "Bienvenido a Spring con ModelAndView");
        // Establecemos la vista
        mv.setViewName("saludo");
        return mv;
    }

    /**
     * El RequestMapping : Establece una ruta, por defecto es de tipo GET
     * Se recomienda usar el GetMapping, es más limpio, además permite tener
     * muchos alias para su uso (estos como un arreglo)
     */
    @RequestMapping("/perfil")
    public String perfil(Model model) {

        Usuario usuario = new Usuario();
        usuario.setNombre("José");
        usuario.setApellido("Robles");
        usuario.setEmail("jose@gmail.com");

        // Enviando un usuario
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));

        return "perfil";
    }

    /**
     * Con el ModelAttribute podemos pasar valores a todas las
     * páginas que estén relacionadas al mismo controlador,
     * muy útil para enviar listas o datos que se repiten
     * para los formularios.
     */
    @ModelAttribute("usuarios") // Key
    public List<Usuario> poblarUsuarios() {
        List<Usuario> usuarios = Arrays.asList(
            new Usuario("José", "Robles", "joss@gmail.com"),
            new Usuario("Andrés", "Guzmán", "andress@gmail.com"),
            new Usuario("John", "Doe", "john@gmail.com"),
            new Usuario("Jane", "Doe", "jane@gmail.com")
        );
        // Value
        return usuarios;
    }

    @RequestMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("titulo", textoListar);
        return "listar";
    }

}
