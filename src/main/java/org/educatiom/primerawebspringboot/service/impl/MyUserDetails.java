package org.educatiom.primerawebspringboot.service.impl;

import org.educatiom.primerawebspringboot.domain.entities.Role;
import org.educatiom.primerawebspringboot.domain.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Adaptador para Spring Security que envuelve la entidad {@link User} de la aplicación.
 * Transforma los datos del usuario (username, password, rol) a la interfaz {@link UserDetails}
 * que es requerida por el proceso de autenticación de Spring Security.
 */
public class MyUserDetails implements UserDetails {


    public MyUserDetails(User user) {
        this.user = user;
    }

    private User user;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * Devuelve la contraseña cifrada del usuario.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Devuelve el nombre de usuario (username).
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indica si la cuenta del usuario no ha expirado.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indica si la cuenta del usuario no está bloqueada.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indica si las credenciales (contraseña) no han expirado.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indica si el usuario está habilitado (activo).
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
