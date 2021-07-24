package com.bolsadeideas.springboot.di.app.models.service;

import org.springframework.stereotype.Component;

/*
* Indicamos que será como un Singleton
* y solo es necesario inyectarlo. Para hacerlo
* podemos usar @Component o @Service, la única
* diferencia es semántica
* */
//@Component("miServicioSimple")// También puede tener un identificador
public class MiServicio implements IServicio {
    public String operacion() {
        return "ejecutando algún proceso importante simple...";
    }

}
