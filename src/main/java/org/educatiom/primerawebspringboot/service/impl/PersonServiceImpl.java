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
    public Person saveOrUpdatePerson(Person person) {
        if (person.getId() != null) {
            logger.info("Actualizando persona con ID: {}", person.getId());
            return personRepository.save(person);
        } else {
            logger.info("Creando nueva persona.");
            return personRepository.save(person);
        }
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
