package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; // importante
import java.nio.file.Paths; // importante
import java.util.Map;
import java.util.UUID;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    @Qualifier("clienteService")
    private IClienteService clienteService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("listar")
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

        Pageable pageRequest = PageRequest.of(page, 5); // página - cantidad

        Page<Cliente> clientes = clienteService.findAll(pageRequest);

        PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clientes);

        model.addAttribute("page", pageRender);

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

    @GetMapping(value = "ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Map<String,Object> model, RedirectAttributes flash) {

        Cliente cliente = clienteService.findOne(id);

        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Detalle cliente: " + cliente.getNombre());
        return "ver";
    }

    @PostMapping(value = "/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model,
                @RequestParam("file") MultipartFile foto, RedirectAttributes flash,
                SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Clientes");
            return "form";
        }

        if (!foto.isEmpty()) {

            // Generamos un nombre único y que nunca se repita
            String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();

            // Ruta uploads concatenado con el filename del archivo.
            Path rootPath = Paths.get("uploads").resolve(uniqueFilename);

            // Convirtiendo la ruta a absoluta
            Path rootAbsolutePath = rootPath.toAbsolutePath();

            // Mostrando en consola para ver más información (opcional)
            log.info("rootPath: " + rootPath); // Path relativo al proyecto
            log.info("rootAbsolutePath: " + rootAbsolutePath); // path absoluto

            try {
                // Hace una copia de la foto en el path absoluto
                Files.copy(foto.getInputStream(), rootAbsolutePath);
                // Enviamos mensaje de confirmación
                flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename+"'");
                // Guardamos en el objeto cliente
                cliente.setFoto(uniqueFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        String mensajeFlash = (cliente.getId() != null) ? "Cliente editado con éxito" : "Cliente Creado con éxito";

        clienteService.save(cliente);
        status.setComplete();// Limpia el session
        // Envía un mensaje flash a la vista (por el session y la petición donde ocurre hasta donde finaliza luego se destruye)
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/listar";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

        Cliente cliente = null;

        if (id > 0) {
            cliente = clienteService.findOne(id);

            if (cliente == null) {
                flash.addFlashAttribute("error", "El ID del cliente no existe en la BD");
                return "redirect:/listar";
            }

        } else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser menor o igual a cero");
            return "redirect:/listar";
        }

        model.put("cliente", cliente);
        model.put("titulo", "Editar Cliente");
        return "form";
    }

    @GetMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        if (id > 0) {
            clienteService.delete(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito");
        }

        return "redirect:/listar";
    }

}
