package com.pabloagustin.springboothorario.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Component("horarioInterceptor")
public class HorarioInterceptor  implements HandlerInterceptor {

	@Value("${config.horario.apertura}")
	private Integer apertura;
	@Value("${config.horario.cierre}")
	private Integer cierre;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// Lo primero obtenemos la hora actual
		Calendar calendar = Calendar.getInstance();
		int hora = calendar.get(Calendar.HOUR_OF_DAY);

		// Comparamos si la hora esta dentro del rango
		if(hora >= apertura && hora < cierre){

			// String Builder de Java para manejos de String, crea Strings que sean mutables
			StringBuilder mensaje = new StringBuilder("Bienvenido al horario de atencion de clientes");
			mensaje.append(", atendemos desde las ");
			mensaje.append(apertura);
			mensaje.append("hrs. ");
			mensaje.append("hasta las ");
			mensaje.append(cierre);
			mensaje.append("hrs. ");
			mensaje.append("Gracias por su visita!");
			// Pasamos los mensajes a los atributos del request
			request.setAttribute("mensaje", mensaje.toString());
			return true;
		}
		// Que pasa si es FALSO? -> Redirigimos al path the la vista 'CERRADO'
		response.sendRedirect(request.getContextPath().concat("/cerrado"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// Para pasar el horario a la vista, al HTML con modelAndView
		String mensaje = (String) request.getAttribute("mensaje");

		// Evitar el error NullPointerException
		if (modelAndView != null & handler instanceof HandlerMethod){
			modelAndView.addObject("horario", mensaje);
		}


	}
}
