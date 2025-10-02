package org.educatiom.primerawebspringboot.config;

import org.educatiom.primerawebspringboot.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Clase de configuración principal para Spring Security.
 * Define la cadena de filtros de seguridad, las reglas de autorización
 * y la configuración de autenticación basada en la base de datos (UserDetailsServiceImpl).
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Define el servicio personalizado que carga los detalles del usuario
     * desde la base de datos (implementación de UserDetailsService).
     * @return Implementación del servicio de detalles de usuario.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
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
                        .permitAll()
                        .defaultSuccessUrl("/persons", true))
                .logout(l -> l.permitAll())
                .exceptionHandling(e -> e.accessDeniedPage("/403"));
        return httpSecurity.build();
    }

    /**
     * Define el codificador de contraseñas (BCrypt) utilizado para cifrar y verificar las contraseñas.
     * @return Instancia de BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el proveedor de autenticación que utiliza UserDetailsService
     * y el PasswordEncoder para verificar las credenciales.
     * @return Instancia de DaoAuthenticationProvider.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Define el AuthenticationManager, que es el punto principal para la autenticación
     * y utiliza el proveedor de autenticación configurado.
     * @param httpSecurity Objeto de configuración HTTP.
     * @return Instancia de AuthenticationManager.
     * @throws Exception si la configuración falla.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
        return authenticationManagerBuilder.build();
    }

}