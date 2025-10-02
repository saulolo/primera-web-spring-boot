package org.educatiom.primerawebspringboot.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(max = 50, min = 3, message = "El username no puede tener mas de 50 caracteres y menos de 3.")
    @NotBlank(message = "El username es obligatorio.")
    @Column(name = "username")
    String username;

    @Size(message = "El password no puede tener mas de 15 caracteres y menos de 5.")
    @Column(name = "password")
    String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Size(max = 50, min = 3, message = "El rol no puede tener mas de 50 caracteres y menos de 3.")
    @NotBlank(message = "El rol es obligatorio.")
    @Column(name = "role")
    String role;

    public User() {
    }

    public User(String username, String password, boolean enabled, String role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", role='" + role + '\'' +
                '}';
    }
}
