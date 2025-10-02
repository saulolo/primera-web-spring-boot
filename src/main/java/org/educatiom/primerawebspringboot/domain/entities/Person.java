package org.educatiom.primerawebspringboot.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "persons")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Person {

    @Id
    @Column(name = "person_id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(max = 50, min = 3, message = "El nombre no puede tener mas de 50 caracteres y menos de 3.")
    @NotBlank(message = "El nombre es obligatorio.")
    String name;

    @Size(max = 50, min = 3, message = "El apellido no puede tener mas de 50 caracteres y menos de 3.")
    @NotBlank(message = "El apellido es obligatorio.")
    @Column(name = "last_name")
    String lastName;

    @Size(max = 50, min = 3, message = "El email no puede tener mas de 50 caracteres y menos de 3.")
    @Email(message = "El email no es válido.")
    @NotBlank(message = "El email es obligatorio.")
    String email;

    @NotNull(message = "El género no puede ser nulo.")
    String gender;

    @Size(max = 500, message = "la nota no puede tener mas de 500 caracteres.")
    String note;

    @NotNull(message = "El campo del estado civil no puede ser nulo.")
    @Column(name = "marital_status")
    Boolean maritalStatus;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    @Past(message = "La fecha de nacimiento no puede ser en el futuro.")
    @DateTimeFormat(pattern = "(yyyy-MM-dd")
    @Column(name = "birth_date")
    Date birthDate;

    @NotBlank(message = "La profesión es obligatorio.")
    String profession;

    @Min(value = 100_000, message = "El salario mínimo es de 100.000")
    @Max(value = 100_000_000, message = "El salario máximo es de 100.000.000")
    Long salary;

    @Min(value = 1, message = "La edad no puede ser menos de 1 año.")
    @Max(value = 150, message = "La edad no puede ser mayor de 150 años.")
    int age;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    LocalDate createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    LocalDate updatedAt;


    public Person() {
    }

    public Person(String name, String lastName, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(String name, String lastName, String email, String gender, String note,
                  Boolean maritalStatus, Date birthDate, String profession, Long salary, int age) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.note = note;
        this.maritalStatus = maritalStatus;
        this.birthDate = birthDate;
        this.profession = profession;
        this.salary = salary;
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", note='" + note + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", birthDate=" + birthDate +
                ", profession='" + profession + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
