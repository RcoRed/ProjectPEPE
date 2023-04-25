package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AbstractPersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByMail(String mail);
}
