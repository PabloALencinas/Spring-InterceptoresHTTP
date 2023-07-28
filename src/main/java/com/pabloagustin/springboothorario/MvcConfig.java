package com.pabloagustin.springboothorario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("horarioInterceptor")
	private HandlerInterceptor horario;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Podemos usar addPathPatterns y agregar solo a index para particular
		// PERO podemos usar excludePathPatterns con cerrado para que se aplique a todos MENOS a cerrado!
		registry.addInterceptor(horario).excludePathPatterns("/cerrado");
	}
}
