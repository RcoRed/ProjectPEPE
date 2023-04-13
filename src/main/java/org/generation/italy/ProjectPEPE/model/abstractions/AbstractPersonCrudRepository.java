package org.generation.italy.ProjectPEPE.model.abstractions;

import org.generation.italy.ProjectPEPE.model.entities.Food;
import org.generation.italy.ProjectPEPE.model.entities.Person;

import java.util.Set;

public interface AbstractPersonCrudRepository extends AbstractGenericCrudRepository<Person> {
    void removeIngredients(Set<Food> foodList);
    void removeIngredients(Food f);

    void addIngredientsToFoodStorage(Set<Food> foodList);
    void addIngredientsToFoodStorage(Food f);
}
