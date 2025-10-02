package org.educatiom.primerawebspringboot.service.impl;

import org.educatiom.primerawebspringboot.domain.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

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


    /**
     * Devuelve los roles/autoridades del usuario como una colección.
     * @return Una colección que contiene el rol del usuario como SimpleGrantedAuthority.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        return Arrays.asList(authority);
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
