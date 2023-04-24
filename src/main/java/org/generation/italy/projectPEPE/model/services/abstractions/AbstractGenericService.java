package org.generation.italy.projectPEPE.model.services.abstractions;

import org.generation.italy.projectPEPE.model.entities.Food;
import org.generation.italy.projectPEPE.model.entities.FoodStorage;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.entities.enums.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AbstractGenericService {

    Optional<Recipe> findById(long id);

    Iterable<FoodStorage> findByPersonAndFood(Person person, Food food);

    Iterable<Recipe> findByNameContains(String name);

    Iterable<Recipe> findByDiet(Diet diet);

    Iterable<Recipe> findByDish(Dish dish);

    Iterable<Recipe> findByPersonDiet(Person person);

    Iterable<Recipe> findByAvoidingIngredients(Person person);
   // Iterable<Recipe> findByAvoidingIngredients(long id);

    Iterable<Recipe> findByDifficulty(Difficulty difficulty);

    Iterable<Recipe> findByToCook(boolean toCook);
    Iterable<Recipe> findAll();
}
