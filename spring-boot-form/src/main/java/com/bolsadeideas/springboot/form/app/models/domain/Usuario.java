package com.bolsadeideas.springboot.form.app.models.domain;

//import com.bolsadeideas.springboot.form.app.validation.IdentificadorRegex;

import com.bolsadeideas.springboot.form.app.validation.Requerido;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class Usuario {

    // No se va a validar este atributo
    //@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]")
    //@IdentificadorRegex
    private String identificador;

    @NotEmpty
    private String nombre;

    /*
      El mensaje personalizado es opcional, además si
      creamos un archivo llamado messages.properties podremos
      agregar mensajes personalizados de manera más eficiente,
      ya sea global o para cada atributo, sin hardcodear el mensaje
     */
    //@NotEmpty
    @Requerido
    private String apellido;

    @NotEmpty
    @Size(min = 3, max = 8, message = "La longitud del usuario debe estar entre 3 y 8 caracteres")
    private String username;

    //@NotBlank  Permite validar que no esté vacío y no tenga espacios en blancos
    @NotEmpty(message = "El campo email no puede estar vacío")
    @Email // no usar el de hibernate
    private String email;

    //@NotEmpty Usando una clase para validar
    private String password;

    @NotNull // exclusivo para objetos no para String, para int se usa Min(num)
    @Min(5)
    @Max(5000)
    private Integer cuenta;

    //@DateTimeFormat(pattern = "yyyy-MM-dd") // Que cumpla el siguiente formato (retorna null sino cumple)
    @NotNull // Verificamos que no sea null
    @Future // Debe aceptar fecha futura no pasada ni presente
    private Date fechaNacimiento;
    // El datepicker retorna el formato yyyy-MM-dd pero visualmente cambia según nuestra referencia local

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

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
