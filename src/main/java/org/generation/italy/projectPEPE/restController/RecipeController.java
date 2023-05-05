package org.generation.italy.projectPEPE.restController;

import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import org.generation.italy.projectPEPE.dtos.FullRecipeDto;
import org.generation.italy.projectPEPE.dtos.SimpleRecipeDto;
import org.generation.italy.projectPEPE.model.entities.Ingredient;
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
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "api/recipe")
public class RecipeController {

    private AbstractGenericService service;

    @Autowired
    public RecipeController(AbstractGenericService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullRecipeDto> findById(@PathVariable long id){
        Optional<Recipe> result = service.findRecipeById(id);
        if(result.isPresent()){
            Recipe recipe = result.get();
            Set<Ingredient> ingredients = service.findIngredientsByRecipe(result.get());
            recipe.setIngredients(ingredients);
            return ResponseEntity.ok().body(FullRecipeDto.fromEntity(recipe));
        }
        return ResponseEntity.notFound().build();
    }
    ///////
    @GetMapping("/avoidIngredients")
    public ResponseEntity<Iterable<SimpleRecipeDto>> findByAvoidingIngredients(Person person){
        Iterable<Recipe> result = service.findByAvoidingIngredients(person);
        return ResponseEntity.ok().body(SimpleRecipeDto.fromEntityIterator(result));
    }

    @GetMapping()
    public ResponseEntity<Iterable<SimpleRecipeDto>> recipeGeneral(@RequestParam(required = false) String namePart,
                                                                   @RequestParam(required = false) Diet diet,
                                                                   @RequestParam(required = false) Dish dish,
                                                                   @RequestParam(required = false) Person person,
                                                                   @RequestParam(required = false) Difficulty difficulty,
                                                                   @RequestParam(required = false) Boolean toCook){
        Iterable<Recipe> result;
        if(person != null){
            result = service.findByPersonDiet(person);
            return ResponseEntity.ok().body(SimpleRecipeDto.fromEntityIterator(result));
        }
        if(dish != null){
            result = service.findByDish(dish);
            return ResponseEntity.ok().body(SimpleRecipeDto.fromEntityIterator(result));
        }
        System.out.println("alalalalalalaalal" + diet + difficulty + toCook);
        result = service.findRecipeByFilters(diet, difficulty, toCook, namePart, null); // metodo della morte
        return ResponseEntity.ok().body(SimpleRecipeDto.fromEntityIterator(result));
    }
}