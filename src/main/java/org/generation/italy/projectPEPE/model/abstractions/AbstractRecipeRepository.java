package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.generation.italy.projectPEPE.model.SQLQueries.SQL_FIND_RECIPE_BY_AVOID_FOOD;

public interface AbstractRecipeRepository extends JpaRepository<Recipe, Long> {
    Iterable<Recipe> findByNameContains(String name);

    Iterable<Recipe> findByDiet(Diet diet);

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




}
/*
*
* SELECT r.name, r.description
            FROM recipe as r
            WHERE r.id_recipe NOT IN (SELECT i.id_recipe
                                 FROM avoiding_food as af
                                 JOIN person as p
                                 ON af.id_person = 10000
                                 JOIN ingredient as i
                                 USING (id_food)
                                 GROUP BY i.id_recipe)
*
* */