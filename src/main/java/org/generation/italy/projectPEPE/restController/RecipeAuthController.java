package org.generation.italy.projectPEPE.restController;

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
@RequestMapping(value = "api/v1/recipeauth")
public class RecipeAuthController {

    private AbstractGenericService service;

    @Autowired
    public RecipeAuthController(AbstractGenericService service) {
        this.service = service;
    }

    @GetMapping("/foodstorage")
    public ResponseEntity<Iterable<SimpleRecipeDto>> findRecipeByFoodStorageOfPerson(@RequestParam(required = false) Long idPerson){
        Iterable<Recipe> result;
        if (idPerson != null){
            Optional<Person> optionalPerson = service.findPersonById(idPerson);
            if(optionalPerson.isPresent()){
                Person person = optionalPerson.get();
                result = service.findRecipeByFoodStorageOfPerson(person);
                return ResponseEntity.ok().body(SimpleRecipeDto.fromEntityIterator(result));
            }
        }
        return ResponseEntity.badRequest().build();
    }
}
