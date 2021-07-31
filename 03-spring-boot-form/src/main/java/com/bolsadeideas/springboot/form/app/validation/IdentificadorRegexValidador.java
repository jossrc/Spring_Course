package com.bolsadeideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// El Generic recibe la Anotación y el tipo de dato que nuestros campos tienen y que será validados
public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Si hace match retorna true, en caso contrario retorna false
        return value.matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]");
    }
}
