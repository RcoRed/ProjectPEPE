package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Food;
import org.generation.italy.projectPEPE.model.entities.FoodStorage;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractFoodStorageRepository extends JpaRepository<FoodStorage,Long> {
    Iterable<FoodStorage> findByPersonAndFood(Person person,Food food);
}
