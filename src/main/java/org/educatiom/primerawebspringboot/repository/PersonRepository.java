package org.educatiom.primerawebspringboot.repository;

import org.educatiom.primerawebspringboot.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
