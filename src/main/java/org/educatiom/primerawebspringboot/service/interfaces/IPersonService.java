package org.educatiom.primerawebspringboot.service.interfaces;

import org.educatiom.primerawebspringboot.domain.entities.Person;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para las operaciones de negocio de la entidad {@link Person}.
 * <p>
 * Define el contrato de los métodos que la capa de servicio debe implementar para
 * gestionar las personas en la aplicación.
 * </p>
 */
public interface IPersonService {

    /**
     * Recupera una lista de todas las personas.
     * @return Una lista de objetos {@link Person}.
     */
    List<Person> getAllPersons();

    /**
     * Busca una persona por su ID.
     * @param id El ID de la persona.
     * @return Un {@link Optional} que contiene la persona si se encuentra, o un Optional vacío si no.
     */
    Optional<Person> getPersonById(Long id);

    /**
     * Guarda o actualiza una persona en la base de datos.
     * <p>
     * Si la persona tiene un ID válido, se actualizará. De lo contrario, se creará una nueva.
     * </p>
     * @param person La persona a guardar o actualizar.
     * @return El objeto {@link Person} guardado o actualizado.
     */
    Person saveOrUpdatePerson(Person person);

    /**
     * Elimina una persona por su ID.
     * @param id El ID de la persona a eliminar.
     */
    void deletePerson(Long id);

    /**
     * Cuenta el número total de personas en la base de datos.
     * @return El número de personas.
     */
    long countPersons();

}
