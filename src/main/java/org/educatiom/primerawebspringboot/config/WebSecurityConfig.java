package org.educatiom.primerawebspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración para Spring Security.
 * Define la cadena de filtros de seguridad y los usuarios en memoria para autenticación.
 */

@Configuration
public class WebSecurityConfig {

    /**
     * Define y gestiona los detalles de los usuarios en memoria (InMemoryUserDetailsManager).
     *
     * @return Un gestor de usuarios que contiene un usuario de rol "USER" y otro de rol "ADMIN".
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user1 = User.builder()
                .username("user1")
                .password("$2a$10$xYt68V0BqfCxOLwsdRLxHeniMk3eJ54cJ9OLZEiV/LW5o.lOxCFh2")
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password("$2a$10$xYt68V0BqfCxOLwsdRLxHeniMk3eJ54cJ9OLZEiV/LW5o.lOxCFh2")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }

    /**
     * Configura la cadena de filtros de seguridad HTTP y las reglas de autorización.
     *
     * Permite acceso sin autenticación a /persons, requiere ADMIN para rutas de gestión
     * y autenticación para cualquier otra petición.
     *
     * Configura el formulario de login, la funcionalidad de logout y la página de acceso denegado (/403).
     *
     * @param httpSecurity Objeto para configurar la seguridad web.
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
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())
                .logout(l -> l.permitAll())
                .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return httpSecurity.build();
    }

    /**
     * Provee el bean de codificación de contraseñas para Spring Security.
     *
     * Utiliza BCrypt para el hashing seguro de contraseñas.
     *
     * @return Una instancia de {@code BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
