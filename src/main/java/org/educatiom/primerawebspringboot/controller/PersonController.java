package org.educatiom.primerawebspringboot.controller;

import org.educatiom.primerawebspringboot.domain.entities.Person;
import org.educatiom.primerawebspringboot.service.impl.PersonServiceImpl;
import org.educatiom.primerawebspringboot.service.interfaces.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.Thymeleaf;

import java.util.List;

/**
 * Controlador para la gestión de personas en la aplicación web.
 * <p>
 * Este controlador maneja todas las solicitudes HTTP relacionadas con las operaciones
 * CRUD (Crear, Leer, Actualizar, Borrar) para la entidad {@link Person}.
 * Utiliza {@link Thymeleaf} para renderizar las vistas.
 * </p>
 */
@Controller
@RequestMapping("persons")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }


    /**
     * Maneja la solicitud GET para mostrar la lista de todas las personas.
     * <p>
     * Esta vista mostrará una tabla con los datos de todas las personas
     * almacenadas en la base de datos.
     * </p>
     * @param model El objeto {@link Model} para pasar datos a la vista.
     * @return El nombre de la vista HTML (`person/viewPersons`).
     */
    @GetMapping({"/viewPersons"})
    public String getAllPersons(Model model) {
        List<Person> personList = personService.getAllPersons();
        model.addAttribute("personList", personList);
        return "person/viewPersons";
    }

    /**
     * Maneja la solicitud GET para mostrar el formulario de registro de una nueva persona.
     *
     * @param model El objeto {@link Model} para pasar datos a la vista.
     * @return El nombre de la vista HTML (`person/formAddPerson`).
     */
    @GetMapping("/formNewPerson")
    public String showFormAddPerson(Model model) {
        Person per = new Person();
        model.addAttribute("per", per);
        return "person/formAddPerson";
    }


    @PostMapping("/saveOrUpdatePerson")
    public String saveOrUpdatePerson(@ModelAttribute Person person) {
        personService.saveOrUpdatePerson(person);
        return "redirect:/persons/viewPersons";
    }


    @GetMapping("/formUpdatePerson/{id}")
    public String showFormUpdatePerson(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
        model.addAttribute("per", person);
        return "person/formAddPerson";
    }


    @PostMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        logger.info("Persona con ID {} eliminada exitosamente. ✔", id);
        return "redirect:/persons/viewPersons";
    }


}
