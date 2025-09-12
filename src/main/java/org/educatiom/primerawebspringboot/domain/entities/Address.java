package org.educatiom.primerawebspringboot.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    String street;
    String number;
    String zipCode;
    String department;
    String city;
    String district;

}
