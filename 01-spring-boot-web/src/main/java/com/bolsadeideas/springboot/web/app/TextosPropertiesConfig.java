package com.bolsadeideas.springboot.web.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Se importan las anotaciones para usar properties
 * desde otro archivo, para que funcione se agrega
 * la ruta con el classpath *
 */
@Configuration
@PropertySources({
       @PropertySource("classpath:textos.properties")
})
public class TextosPropertiesConfig {}
