package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Food;
import org.generation.italy.projectPEPE.model.entities.FoodStorage;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractFoodStorageRepository extends JpaRepository<FoodStorage,Long> {
    //void removeIngredients(Set<Food> foodList);
    //void removeIngredients(Food f);

    //void addIngredientsToFoodStorage(Set<Food> foodList);
    //void addIngredientsToFoodStorage(Food f);
    Iterable<FoodStorage> findByPersonAndFood(Person person,Food food);
}
