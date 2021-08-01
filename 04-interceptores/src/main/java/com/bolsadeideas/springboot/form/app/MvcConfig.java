package com.bolsadeideas.springboot.form.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired // inyectamos a través de la interfaz
    @Qualifier("tiempoTranscurridoInterceptor") // seleccionamos el componente (puede haber más de uno)
    private HandlerInterceptor tiempoTranscurridoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // se inyecta el interceptor para todas las rutas
        registry.addInterceptor(tiempoTranscurridoInterceptor);
    }
}
