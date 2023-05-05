package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;

import java.util.ArrayList;

public interface AbstractRecipeRepositoryCustom {
    Iterable<Recipe> findRecipeByFilters(Diet diet, Difficulty difficulty, Boolean isToCook, String name);
}
