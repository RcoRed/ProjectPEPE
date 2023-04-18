package org.generation.italy.projectPEPE.restController;

import org.generation.italy.projectPEPE.dtos.RecipeDto;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.services.abstractions.AbstractGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/recipe")
public class RecipeController {

    private AbstractGenericService service;

    @Autowired
    public RecipeController(AbstractGenericService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<Iterable<RecipeDto>> recipeGeneral(@RequestParam(required = false) String recipeName){
        if (recipeName != null){
            Iterable<Recipe> result = service.findByNameContains(recipeName);
            return ResponseEntity.ok().body(RecipeDto.fromEntityIterator(result));
        }
        return null;
    }



}
