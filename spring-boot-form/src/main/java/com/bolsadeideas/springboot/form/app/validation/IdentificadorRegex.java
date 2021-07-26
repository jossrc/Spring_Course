package com.bolsadeideas.springboot.form.app.validation;

/*
* Importamos estos :
* */
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// Se relaciona con la clase que contiene los métodos que harán validaciones
@Constraint(validatedBy = IdentificadorRegexValidador.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface IdentificadorRegex {

    // Agregamos los métodos que tiene alguna anotación de validación
    // En este caso se usa del @NotEmpty (ctrl + click para ir y copiar)

    String message() default "Identificador inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
