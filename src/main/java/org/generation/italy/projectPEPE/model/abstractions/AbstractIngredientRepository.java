package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Ingredient;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AbstractIngredientRepository extends JpaRepository<Ingredient, Long> {
//
//    @Query("SELECT i.id, i.recipe, i.food, f.name FROM Ingredient i JOIN Food f WHERE i.recipe = :i.recipe")
//    @Query("SELECT i.id, i.recipe, i.food, f.name FROM Ingredient i JOIN Food f WHERE i.recipe = :recipe")
//    @Query("SELECT i.id FROM Ingredient i JOIN Food f JOIN Recipe r WHERE r.id = :recipe")
//    @Query("from Ingredient i join Food f where i.recipe = :recipe") // vede se ingredients è null
    @Query(value = "SELECT i.id_ingredient, i.id_recipe, i.id_food, i.weight, i.is_optional, i.quantity, " +
            "f.name FROM ingredient AS i JOIN food AS f USING (id_food) WHERE id_recipe = 1", nativeQuery = true)
    Set<Ingredient> findIngredientsByRecipe(Recipe recipe);

//    SELECT id_ingredient, id_recipe, id_food, f.name
//    FROM ingredient AS i
//    JOIN food AS f
//    USING (id_food)
//    WHERE id_recipe = 12
}
