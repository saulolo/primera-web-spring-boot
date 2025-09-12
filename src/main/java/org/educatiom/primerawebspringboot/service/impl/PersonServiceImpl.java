package org.educatiom.primerawebspringboot.service.impl;

import org.educatiom.primerawebspringboot.domain.entities.Person;
import org.educatiom.primerawebspringboot.repository.PersonRepository;
import org.educatiom.primerawebspringboot.service.interfaces.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements IPersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public Person createPerson(Person person) {
        logger.info("Datos persistidos exitosamente en la BD. ✔");
        return personRepository.save(person);

    }

    @Override
    public Person updatePerson(Long id, Person person) {
        if (personRepository.existsById(id)) {
            person.setId(id);
            return personRepository.save(person);
        }
        throw new RuntimeException("La persona con ID" + id + " No se encuentra en la BD.");
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
        logger.info("Persona eliminada exitosamente. ✔");

    }

    @Override
    public long countPersons() {
        return personRepository.count();
    }
}
