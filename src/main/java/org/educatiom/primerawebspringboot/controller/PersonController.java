package org.educatiom.primerawebspringboot.controller;

import org.educatiom.primerawebspringboot.domain.entities.Person;
import org.educatiom.primerawebspringboot.service.impl.PersonServiceImpl;
import org.educatiom.primerawebspringboot.service.interfaces.IPersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("persons")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }


    @GetMapping({"/viewPersons"})
    public String getAllPersons(Model model) {
        List<Person> personList = personService.getAllPersons();
        model.addAttribute("personList", personList);
        return "person/viewPersons";
    }

    @GetMapping("/formNewPerson")
    public String showFormAddPerson(Model model) {
        Person per = new Person();
        model.addAttribute("per", per);
        List<Person> personList = personService.getAllPersons();
        model.addAttribute("personList", personList);
        return "person/formAddPerson";
    }

    //Agregar nueva persona
    @PostMapping("/addNewPerson")
    public String addNewPerson(@ModelAttribute Person person) {
        personService.createPerson(person);
        return "redirect:/persons/viewPersons";
    }



}
