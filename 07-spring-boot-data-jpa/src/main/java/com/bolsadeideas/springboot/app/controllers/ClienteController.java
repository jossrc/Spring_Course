package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;
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

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    @Qualifier("clienteService")
    private IClienteService clienteService;

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
            // Ruta completamente separada del proyecto
            String rootPath = "C://Temp//uploads";
            try {
                // Obtenemos los bytes
                byte[] bytes = foto.getBytes();
                // Obtenemos la ruta completa (raíz + nombre ruta de la foto)
                Path rutaCompleta = Paths.get(rootPath + "//"+foto.getOriginalFilename());
                // Escribimos y guardamos la imagen
                Files.write(rutaCompleta, bytes);
                // Enviamos mensaje de confirmación
                flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename()+"'");
                // Guardamos en el objeto cliente
                cliente.setFoto(foto.getOriginalFilename());
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
