package com.bolsadeideas.springboot.error.app.controllers;

import com.bolsadeideas.springboot.error.app.errors.UsuarioNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

// Permite manejar errores y excepciones
@ControllerAdvice
public class ErrorHandlerController {

    // Se establece el tipo de Exception, usando llaves podemos agregar varias (arreglo)
    @ExceptionHandler(ArithmeticException.class)
    public String aritmeticaError(ArithmeticException ex, Model model) {

        model.addAttribute("error", "Error de aritmética");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());

        return "error/aritmetica";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String numeroFormatoError(NumberFormatException ex, Model model) {

        model.addAttribute("error", "Error: Formato número inválido");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("timestamp", new Date());

        return "error/numero-formato";
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public String numeroFormatoError(UsuarioNoEncontradoException ex, Model model) {

        model.addAttribute("error", "Error: Usuario no encontrado");
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("status", HttpStatus.NOT_FOUND);
        model.addAttribute("timestamp", new Date());

        return "error/generica";
    }

}
