package com.bolsadeideas.springboot.form.app.editors;

import com.bolsadeideas.springboot.form.app.services.PaisService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

// Agregamos component para inyectar el PaisServiceImpl usando una interface
@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private PaisService service;

    @Override
    public void setAsText(String idString) throws IllegalArgumentException {

        if(idString != null && idString.length() > 0) {
            try {
                Integer id = Integer.parseInt(idString);
                this.setValue(service.obtenerPorId(id));
            } catch (NumberFormatException e) {
                // Si falla que el valor sea null
                setValue(null);
            }
        } else {
            setValue(null);
        }
    }
}
