# CONTINUACION DEL CURSO SPRING & SPRING BOOT

# SECCION 6 - INTERCEPTORES HTTP: Horario de acceso a clientes

## 1) Creacion de proyecto

    - Seteamos el proyecto con las dependencias que venimos trabajando

        -> DevTools
        -> Spring Web
        -> Thymeleaf

    - Creamos toda la configuracion inicial como veniamos trabajando

        -> Controller: App controller con el GET map para mostrar la vista index
        -> Creamos la vista index en el template para mostrarla despues

    - Dentro del properties definimos el rango de horarios para la atencion de clientes
    
        -> ESTA DEFINICION DE HORARIO ES LA QUE VAMOS A INYECTAR EN EL INTERCEPTOR DE HORARIO
        PARA LUEGO DETERMINAR SI, CON EL HORARIO ACTUAL, EL USUARIO ESTA DENTRO DEL RANGO DE LA ATENCION
        DE LO CONTRARIO, VAMOS A REDIRIGIR A UN METODO ALTERNATIVO PARA ELLO.

## 2) Creando el interceptor horario

    - Creamos el package para interceptores
    - Creamos la clase que implementa HandlerInterceptor y le agregamos el @Component para que Spring lo pueda reconocer
    y podamos INYECTAR luego los valores que colocamos como horario apertura y cierre en el properties

    - Para poder inyectar usamos VALUES, por lo que definimos un par de atributos dentro del interceptor
    para el horario de apertura y cierre

## 3) Registrando el interceptor en el MVC config

    - Creamos la clase MvcConfig, @Configuration e implementamos el WebMvcConfigurer.
    - Sobreescribimos el metodo addInterceptor con el nombre del interceptor luego de INYECTARLO con el qualifier

    - OJO! VEREMOS UN ERROR
        -> Ingresamos en horario fuera del rango y nos lleva al error: "localhost te redirigió demasiadas veces"
        -> EL INTERCEPTOR SE APLICA A TODOS LOS METODOS HANDLER! AL INDEX Y AL CERRADO INCLUSIVE
        -> CLARO: Index redirige a cerrado porque esta fuera de horario OK, PERO, CERRADO TAMBIEN ESTA DENTRO
        DEL FUNCIONAMIENTO DEL INTERCEPTOR, por eso el error.

## 4) Para evitar el error NullPointerException (opcional)

    - Agregamos la condicion para modelAndView != null && handler instanceof HandlerMethod

# SECCION 7 - MANEJO DE ERRORES

## 1) Creando nueva aplicacion y controlador

    




















