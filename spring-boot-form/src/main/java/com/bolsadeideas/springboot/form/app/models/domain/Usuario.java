package com.bolsadeideas.springboot.form.app.models.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Usuario {

    // No se va a validar este atributo
    //@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]")
    private String identificador;

    @NotEmpty
    private String nombre;

    /*
      El mensaje personalizado es opcional, además si
      creamos un archivo llamado messages.properties podremos
      agregar mensajes personalizados de manera más eficiente,
      ya sea global o para cada atributo, sin hardcodear el mensaje
     */
    @NotEmpty
    private String apellido;

    @NotEmpty
    @Size(min = 3, max = 8, message = "La longitud del usuario debe estar entre 3 y 8 caracteres")
    private String username;

    @NotEmpty(message = "El campo email no puede estar vacío")
    @Email // no usar el de hibernate
    private String email;

    @NotEmpty(message = "El campo password no puede estar vacío")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}
