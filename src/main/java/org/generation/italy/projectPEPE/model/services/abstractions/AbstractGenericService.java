package org.generation.italy.projectPEPE.model.services.abstractions;

import org.generation.italy.projectPEPE.model.entities.*;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.entities.enums.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface AbstractGenericService {

    Optional<Recipe> findRecipeById(long id);
    Optional<Person> findPersonById(long id);

    Iterable<FoodStorage> findByPersonAndFood(Person person, Food food);

    Iterable<Recipe> findByNameContains(String name);

    Iterable<Recipe> findByDiet(Diet diet);

    Iterable<Recipe> findByDish(Dish dish);

    Set<Ingredient> findIngredientsByRecipe(Recipe recipe);

    Iterable<Recipe> findByPersonDiet(Person person);

    Iterable<Recipe> findByAvoidingIngredients(Person person);
   // Iterable<Recipe> findByAvoidingIngredients(long id);

    Iterable<Recipe> findByDifficulty(Difficulty difficulty);

    Iterable<Recipe> findByToCook(boolean toCook);
    Iterable<Recipe> findAll();

    Iterable<Recipe> findRecipeByFoodStorageOfPerson(Person person);

    Iterable<Recipe> findRecipeByFilters(Diet diet, Difficulty difficulty, Boolean isToCook, String name);
}
