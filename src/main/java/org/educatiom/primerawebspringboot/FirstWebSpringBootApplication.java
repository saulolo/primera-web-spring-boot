package org.educatiom.primerawebspringboot;

import org.educatiom.primerawebspringboot.domain.entities.Person;
import org.educatiom.primerawebspringboot.service.interfaces.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstWebSpringBootApplication implements CommandLineRunner {


    @Autowired
    private IPersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(FirstWebSpringBootApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Person person1 = new Person();
        person1.setName("Sandra");
        person1.setLastName("Uribe");
        person1.setAge(50);

        personService.createPerson(person1);

        System.out.println("Las personas ingresadas fueron: " + personService.countPersons());

        System.out.println("Las personas que se encuentran en la BD son: " + personService.getAllPersons().toString());

    }
}
