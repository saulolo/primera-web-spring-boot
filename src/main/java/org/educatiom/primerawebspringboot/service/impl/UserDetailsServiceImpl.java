package org.educatiom.primerawebspringboot.service.impl;

import org.educatiom.primerawebspringboot.domain.entities.User;
import org.educatiom.primerawebspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Servicio central de Spring Security para la autenticación basada en base de datos.
 * Implementa {@link UserDetailsService} y se encarga de **cargar la información del usuario**
 * desde la tabla 'users' a través del UserRepository.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    /**
     * Localiza al usuario basado en el nombre de usuario (username).
     * Este método es invocado por Spring Security durante el proceso de inicio de sesión.
     *
     * @param username El nombre de usuario que intenta autenticarse.
     * @return Un objeto {@link UserDetails} ({@link MyUserDetails}) con los datos del usuario.
     * @throws UsernameNotFoundException si el usuario no existe en la base de datos.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new MyUserDetails(user);
    }
}
