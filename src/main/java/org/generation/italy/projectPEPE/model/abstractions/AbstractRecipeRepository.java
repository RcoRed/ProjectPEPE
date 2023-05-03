package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Ingredient;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.entities.enums.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AbstractRecipeRepository extends JpaRepository<Recipe, Long> {
    Iterable<Recipe> findByNameContainsIgnoreCase(String name);

    Iterable<Recipe> findByDiet(Diet diet);

    Iterable<Recipe> findByDish(Dish dish);

    @Query("from Recipe r join Person p where r.diet = p.diet")
    Iterable<Recipe> findByPersonDiet(Person person);

    //FUNZIONAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA MA MEGLIOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    @Query("from Recipe r where r.id NOT IN (SELECT i.recipe FROM AvoidingFood af JOIN af.person p JOIN Ingredient i ON af.food = i.food WHERE p = :person) ")
    Iterable<Recipe> findByAvoidingIngredients(Person person);

//rimane per la storia
//    @Query(value = SQL_FIND_RECIPE_BY_AVOID_FOOD,nativeQuery = true)
//    Iterable<Recipe> findByAvoidingIngredients( long pippo);

    Iterable<Recipe> findByDifficulty(Difficulty difficulty);

    Iterable<Recipe> findByToCook(boolean toCook);

    @Query( "FROM Recipe r JOIN Ingredient i ON r = i.recipe JOIN FoodStorage fs ON i.food = fs.food JOIN fs.person p ")
    Iterable<Recipe> findRecipeByPersonStorage(Person person);
}


