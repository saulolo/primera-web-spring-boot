package org.educatiom.primerawebspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración MVC para registrar controladores de vista simples.
 * Implementa {@code WebMvcConfigurer} para personalizar la configuración por defecto.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Registra controladores de vista que mapean directamente una URL a un nombre de vista.
     * Esto evita la necesidad de crear un método de controlador explícito para vistas sencillas.
     *
     * @param registry el registro para añadir controladores de vista.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/login").setViewName("login");

    }
}
