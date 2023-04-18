package org.generation.italy.projectPEPE.restController;

import org.generation.italy.projectPEPE.dtos.RecipeDto;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.services.abstractions.AbstractGenericService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/recipe")
public class RecipeController {

    private AbstractGenericService service;

    @Autowired
    public RecipeController(AbstractGenericService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> findById(@PathVariable long id){
        Optional<Recipe> result = service.findById(id);
        if(result.isPresent()){
            return ResponseEntity.ok().body(RecipeDto.fromEntity(result.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/avoidIngredients")
    public ResponseEntity<Iterable<RecipeDto>> findByAvoidingIngredients(Person person){
        Iterable<Recipe> result = service.findByAvoidingIngredients(person);
        return ResponseEntity.ok().body(RecipeDto.fromEntityIterator(result));
    }

    @GetMapping()
    public ResponseEntity<Iterable<RecipeDto>> recipeGeneral(@RequestParam(required = false) String recipeName,
                                                             @RequestParam(required = false)Diet diet,
                                                             @RequestParam(required = false) Person person,
                                                             @RequestParam(required = false)Difficulty difficulty,
                                                             @RequestParam(required = false) Boolean toCook){
        Iterable<Recipe> result = null;
        if (recipeName != null){
            result = service.findByNameContains(recipeName);
            return ResponseEntity.ok().body(RecipeDto.fromEntityIterator(result));
        }
        if(diet != null){
            result = service.findByDiet(diet);
            return ResponseEntity.ok().body(RecipeDto.fromEntityIterator(result));
        }
        if(person != null){
            result = service.findByPersonDiet(person);
            return ResponseEntity.ok().body(RecipeDto.fromEntityIterator(result));
        }
        if(difficulty != null){
            result = service.findByDifficulty(difficulty);
            return ResponseEntity.ok().body(RecipeDto.fromEntityIterator(result));
        }
        if(toCook != null){
            result = service.findByToCook(toCook);
            return ResponseEntity.ok().body(RecipeDto.fromEntityIterator(result));
        }
        return ResponseEntity.notFound().build();
    }
}