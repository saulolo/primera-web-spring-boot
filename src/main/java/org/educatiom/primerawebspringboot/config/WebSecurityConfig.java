package org.educatiom.primerawebspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Clase de configuración para Spring Security.
 * Define la cadena de filtros de seguridad y los usuarios en memoria para autenticación.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;


    @Autowired
    public void configuthentication(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username, role from users where username=?");
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
