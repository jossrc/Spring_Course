package com.bolsadeideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

/*
* Creando un Editor para modificar el valor después de ser inicializados
*
* */
public class NombreMayusculaEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase().trim());
    }
}
