package org.educatiom.primerawebspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FirstWebSpringBootApplication{

    public static void main(String[] args) {
        SpringApplication.run(FirstWebSpringBootApplication.class, args);

    }

}
