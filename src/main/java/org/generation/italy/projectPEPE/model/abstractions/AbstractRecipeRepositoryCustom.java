package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.entities.enums.Dish;

import java.util.ArrayList;

public interface AbstractRecipeRepositoryCustom {
    Iterable<Recipe> findRecipeByFilters(Diet diet, Difficulty difficulty, Boolean isToCook, String name, Dish dish, Long idPerson);
}
