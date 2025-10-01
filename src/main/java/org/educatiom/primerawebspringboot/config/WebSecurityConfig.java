package org.educatiom.primerawebspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración para Spring Security.
 * Define la cadena de filtros de seguridad y los usuarios en memoria para autenticación.
 */
@Configuration
public class WebSecurityConfig {

    /**
     * Crea y gestiona los detalles de los usuarios en memoria (InMemoryUserDetailsManager).
     * Ideal para entornos de desarrollo y pruebas. Las contraseñas se almacenan cifradas con BCrypt.
     *
     * @return un {@code InMemoryUserDetailsManager} con usuarios de roles "USER" y "ADMIN".
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user1 = User.builder()
                .username("user1")
                .password("{bcrypt}$2a$10$xYt68V0BqfCxOLwsdRLxHeniMk3eJ54cJ9OLZEiV/LW5o.lOxCFh2")
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$xYt68V0BqfCxOLwsdRLxHeniMk3eJ54cJ9OLZEiV/LW5o.lOxCFh2")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    /**
     * Configura la cadena de filtros de seguridad HTTP, definiendo las reglas de autorización.
     * * @param httpSecurity Objeto para configurar la seguridad a nivel de peticiones web.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception si la configuración falla.
     */
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/persons").permitAll()
                        .requestMatchers("/persons/saveOrUpdatePerson").hasAnyRole("ADMIN")
                        .requestMatchers("/persons/formUpdatePerson/*", "/persons/deletePerson/*").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return httpSecurity.build();
    }

}
