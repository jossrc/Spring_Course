package com.bolsadeideas.springboot.horariointerceptor.app.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component
public class HorarioInterceptor  implements HandlerInterceptor {

    // Inyectamos valores provenientes del properties

    @Value("${config.horario.apertura}")
    private Integer apertura;

    @Value("${config.horario.cierre}")
    private Integer cierre;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Calendar calendar = Calendar.getInstance();
        int hora = calendar.get(Calendar.HOUR_OF_DAY);

        if (hora >= apertura && hora < cierre) {
            // Creando un String mutable
            StringBuilder mensaje = new StringBuilder("Bienvenidos al horario de atención a clientes");
            mensaje.append(", atendemos desde las ");
            mensaje.append(apertura);
            mensaje.append("hrs. ");
            mensaje.append("hasta las ");
            mensaje.append(cierre);
            mensaje.append("hrs. ");
            mensaje.append(". Gracias por su visita.");

            request.setAttribute("mensaje", mensaje.toString());

            return true;
        }

        response.sendRedirect(request.getContextPath().concat("/cerrado"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        String mensaje = (String) request.getAttribute("mensaje");
        modelAndView.addObject("horario", mensaje);
    }
}
