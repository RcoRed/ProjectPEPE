package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AbstractRecipeRepository extends JpaRepository<Recipe, Long> {

    Iterable<Recipe> findByNameContains(String name);

    Iterable<Recipe> findByDiet(Diet diet);

    @Query("from Recipe r join Person p where r.diet = p.diet")
    Iterable<Recipe> findByPersonDiet(Person person);

    //non funziona
    @Query("from Recipe r join Ingredient i where i.food != (select af.food from AvoidingFood af join Person p) ")
    Iterable<Recipe> findByAvoidingIngredients(Person person);

    Iterable<Recipe> findByDifficulty(Difficulty difficulty);

    Iterable<Recipe> findByToCook(boolean toCook);




}
