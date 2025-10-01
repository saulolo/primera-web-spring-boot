package org.educatiom.primerawebspringboot.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * Clase de utilidad para generar contraseñas cifradas usando BCrypt.
 * Se utiliza para obtener la versión cifrada de una contraseña simple
 * y poder almacenarla de forma segura en la configuración de usuarios.
 */
public class SecuredPassword {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
