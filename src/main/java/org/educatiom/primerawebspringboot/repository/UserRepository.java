package org.educatiom.primerawebspringboot.repository;

import org.educatiom.primerawebspringboot.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca una entidad de usuario por su nombre de usuario.
     * @param username El nombre de usuario a buscar.
     * @return La entidad User o null si no se encuentra.
     */
    User findByUsername(String username);
}
