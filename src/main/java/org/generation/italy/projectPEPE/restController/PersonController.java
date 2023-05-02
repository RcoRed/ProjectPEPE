package org.generation.italy.projectPEPE.restController;

import org.generation.italy.projectPEPE.dtos.PersonDto;
import org.generation.italy.projectPEPE.dtos.SimpleRecipeDto;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.services.abstractions.AbstractGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "api/person")
public class PersonController {

    private AbstractGenericService service;

    @Autowired
    public PersonController(AbstractGenericService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable long id){
        Optional<Person> result = service.findPersonById(id);
        if(result.isPresent()){
            return ResponseEntity.ok().body(PersonDto.fromEntity(result.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
