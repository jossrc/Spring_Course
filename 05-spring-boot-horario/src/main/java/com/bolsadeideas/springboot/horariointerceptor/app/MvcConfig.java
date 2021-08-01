package com.bolsadeideas.springboot.horariointerceptor.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    @Qualifier("horario")
    private HandlerInterceptor horario;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // El camino siempre debe excluirse del interceptor, ya que el interceptor se ejecuta
        // en todos los archivos
        // Excluyendo para evitar un loop infinito
        registry.addInterceptor(horario).excludePathPatterns("/cerrado");
    }
}
