package org.educatiom.primerawebspringboot.service.interfaces;

import org.educatiom.primerawebspringboot.domain.entities.Person;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    List<Person> getAllPersons();

    Optional<Person> getPersonById(Long id);

    Person saveOrUpdatePerson(Person person);

    void deletePerson(Long id);

    long countPersons();

}
