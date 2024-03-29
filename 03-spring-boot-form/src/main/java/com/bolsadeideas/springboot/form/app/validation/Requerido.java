package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = RequeridoValidador.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface Requerido {

    String message() default "El campo es requerido - usando anotaciones";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
