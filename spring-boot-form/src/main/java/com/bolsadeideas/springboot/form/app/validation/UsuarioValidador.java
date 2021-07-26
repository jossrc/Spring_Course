package com.bolsadeideas.springboot.form.app.validation;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UsuarioValidador implements Validator {

    // El Validator se implementa de springframework

    @Override
    public boolean supports(Class<?> clazz) {
        // Le indicamos que solo es para la clase Usuario
        return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Usuario usuario = (Usuario) target;

        // Creamos una validación si está vacío en el campo password, usando el propertie de password (obligatorio)
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.usuario.password");
        // no recomendado ↑ con password (solo ejemplo)

        /* Otra forma de hacer validaciones
        if (!usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]")) {
            // El nombre del propertie puede ser a nuestro gusto en este caso
            errors.rejectValue("identificador", "pattern.usuario.identificador");
        }
        */

    }
}
