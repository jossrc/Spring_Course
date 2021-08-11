package com.bolsadeideas.springboot.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(getClass());

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//
//        // Obtiene la ruta absoluta (desde el proyecto) y le agrega el file (al inicio)
//        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
//
//        log.info("resourcePath: "+ resourcePath);
//
//        registry.addResourceHandler("/uploads/**") // acepta todos los recursos dentro de uploads
//                .addResourceLocations(resourcePath); // se configura como estático
//
//    }
}
