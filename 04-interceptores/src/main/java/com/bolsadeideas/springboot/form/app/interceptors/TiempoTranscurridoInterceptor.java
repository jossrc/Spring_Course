package com.bolsadeideas.springboot.form.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

// Para hacer inyecciones al controlador X
@Component
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    // Establecemos un Logger para mostrar la info por la consola
    private static final Logger logger = LoggerFactory
            .getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
            throws Exception {

        logger.info("TiempoTranscurridoInterceptor: preHandle() entrando...");

        // Se obtiene el tiempo actual en milisegundos
        long tiempoInicio = System.currentTimeMillis();

        // Guardamos el tiempo inicial en la request
        request.setAttribute("tiempoInicio", tiempoInicio);

        // Simulamos un tiempo de demora de manera aleatoria
        Random random = new Random();
        int demora = random.nextInt(500);

        // Esperamos la ejecución
        Thread.sleep(demora);

        // Cuando es true continúa con la ejecución, si es false finaliza el proceso
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView)
            throws Exception {

        // Se obtiene el tiempo actual (final) en milisegundos
        long tiempoFin = System.currentTimeMillis();

        // Obtenemos el tiempo inicial enviado por la request
        long tiempoInicio = (Long) request.getAttribute("tiempoInicio");

        // Calculamos el tiempo transcurrido
        long tiempoTranscurrido = tiempoFin - tiempoInicio;

        // Enviamos el tiempo transcurrido a la vista
        if (modelAndView != null) {
            modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
        }

        logger.info("Tiempo transcurrido: " + tiempoTranscurrido + " milisegundos");
        logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo...");
    }
}
