package com.pabloagustin.springboothorario.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@Value("${config.horario.apertura}")
	private Integer apertura;
	@Value("${config.horario.cierre}")
	private Integer cierre;

	// Metodo Handler

	// Para ejecutar un proceso
	// Pero en el fondo es para atencion del cliente, funciona a traves de un rango de hora
	@GetMapping({"/", "/index"})
	public String index(Model model){
		model.addAttribute("titulo", "Bienvenido al horario de atencion al cliente");
		return "index";
	}

	// Implementamos el controlador para la vista de 'CERRADO' cuando el interceptor quede en falso por los horarios
	// de apertura y de cierre para los clientes

	@GetMapping("/cerrado")
	public String cerrado(Model model){

		// Mensaje que vamos a concatenar
		StringBuilder mensaje = new StringBuilder("Cerrado. Por favor visitenos desde las ");
		mensaje.append(apertura);
		mensaje.append("Hrs. ");
		mensaje.append(" y las ");
		mensaje.append(cierre);
		mensaje.append("Hrs. Gracias!");


		model.addAttribute("titulo", "Fuera del horario de atencion");
		model.addAttribute("mensaje", mensaje);
		return "cerrado";
	}

}
