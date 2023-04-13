package org.generation.italy.ProjectPEPE.model.implementations;

import org.generation.italy.ProjectPEPE.model.abstractions.AbstractPersonCrudRepository;
import org.generation.italy.ProjectPEPE.model.entities.Food;
import org.generation.italy.ProjectPEPE.model.entities.Person;

import java.util.Optional;
import java.util.Set;

public class PersonCrudRepository implements AbstractPersonCrudRepository {

    @Override
    public Person create(Person entity) {
        return null;
    }

    @Override
    public Optional<Person> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void update(Person entity) {

    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void removeIngredients(Set<Food> foodList) {

    }

    @Override
    public void removeIngredients(Food f) {

    }

    @Override
    public void addIngredientsToFoodStorage(Set<Food> foodList) {

    }

    @Override
    public void addIngredientsToFoodStorage(Food f) {

    }
}
