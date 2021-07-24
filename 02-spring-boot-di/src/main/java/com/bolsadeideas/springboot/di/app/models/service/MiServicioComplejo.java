package com.bolsadeideas.springboot.di.app.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("miServicioComplejo")
//@Primary // Este es para decirle el por defecto, no se recomienda cuando se trabaja con @Qualifier
public class MiServicioComplejo implements IServicio {
    // Cuando se tienen muchos servicios se recomienda usar la anotación primary
    // para que Spring detecte a quién ejecutar
    public String operacion() {
        return "Ejecutando algún proceso importante complicado...";
    }

}
