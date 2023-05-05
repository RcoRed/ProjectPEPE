package org.generation.italy.projectPEPE.restController;

import org.generation.italy.projectPEPE.dtos.SimpleRecipeDto;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.entities.enums.Dish;
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
    public ResponseEntity<Iterable<SimpleRecipeDto>> findRecipeByFoodStorageOfPerson(@RequestParam Long idPerson){
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

    @GetMapping()
    public ResponseEntity<Iterable<SimpleRecipeDto>> recipeGeneral(@RequestParam(required = false) String namePart,
                                                                   @RequestParam(required = false) Diet diet,
                                                                   @RequestParam(required = false) Dish dish,
                                                                   @RequestParam(required = false) Person person,
                                                                   @RequestParam(required = false) Difficulty difficulty,
                                                                   @RequestParam(required = false) Boolean toCook,
                                                                   @RequestParam(required = false) Long idPerson){
        Iterable<Recipe> result;
        if(person != null){
            result = service.findByPersonDiet(person);
            return ResponseEntity.ok().body(SimpleRecipeDto.fromEntityIterator(result));
        }
        if(dish != null){
            result = service.findByDish(dish);
            return ResponseEntity.ok().body(SimpleRecipeDto.fromEntityIterator(result));
        }
        result = service.findRecipeByFilters(diet, difficulty, toCook, namePart, idPerson); // metodo della morte
        return ResponseEntity.ok().body(SimpleRecipeDto.fromEntityIterator(result));
    }
}
