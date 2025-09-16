package org.educatiom.primerawebspringboot.controller;

import org.educatiom.primerawebspringboot.domain.entities.Person;
import org.educatiom.primerawebspringboot.service.interfaces.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.Thymeleaf;

import java.util.Arrays;
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
     * <p>
     * Este método crea una nueva instancia de la clase {@link Person}, la añade al modelo
     * para que el formulario de Thymeleaf la utilice y la complete. Además, carga
     * una lista predefinida de profesiones en el modelo para poblar el menú desplegable
     * en el formulario.
     * </p>
     *
     * @param model El objeto {@link Model} para pasar datos a la vista.
     * @return El nombre de la vista HTML (`person/formAddPerson`).
     */
    @GetMapping("/formNewPerson")
    public String showFormAddPerson(Model model) {
        Person per = new Person();
        model.addAttribute("per", per);
        List<String> professionList = Arrays.asList("Desarrollador", "Tester", "Arquitecto");
        model.addAttribute("professions", professionList);
        return "person/formAddPerson";
    }

    /**
     * Maneja la solicitud POST para guardar o actualizar una persona.
     * <p>
     * Este método es el punto de entrada unificado para crear y actualizar personas.
     * Utiliza {@link ModelAttribute} para enlazar los datos del formulario a un objeto {@link Person}
     * y delega la lógica de negocio al servicio.
     * </p>
     * @param person El objeto {@link Person} que contiene los datos del formulario. Si tiene un ID, se actualizará; de lo contrario, se creará.
     * @return Una redirección a la ruta de la lista de personas para ver los cambios.
     */
    @PostMapping("/saveOrUpdatePerson")
    public String saveOrUpdatePerson(@ModelAttribute Person person) {
        personService.saveOrUpdatePerson(person);
        return "redirect:/persons/viewPersons";
    }

    /**
     * Maneja la solicitud GET para mostrar el formulario de actualización de una persona.
     * <p>
     * Carga los datos de una persona específica por su ID desde el servicio y los
     * añade al modelo para precargar el formulario de edición.
     * </p>
     * @param id El ID de la persona a actualizar, obtenido de la ruta.
     * @param model El objeto {@link Model} para pasar los datos de la persona a la vista.
     * @return El nombre de la vista HTML (`person/formAddPerson`).
     * @throws RuntimeException si la persona con el ID proporcionado no se encuentra.
     */
    @GetMapping("/formUpdatePerson/{id}")
    public String showFormUpdatePerson(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
        model.addAttribute("per", person);
        return "person/formAddPerson";
    }

    /**
     * Maneja la solicitud POST para eliminar una persona de la base de datos.
     * <p>
     * Este método recibe el ID de la persona a eliminar desde la URL y delega
     * la operación al servicio. Redirige a la lista de personas después de la eliminación.
     * </p>
     * @param id El ID de la persona a eliminar.
     * @return Una redirección a la ruta de la lista de personas.
     */
    @PostMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        logger.info("Persona con ID {} eliminada exitosamente. ✔", id);
        return "redirect:/persons/viewPersons";
    }

}
