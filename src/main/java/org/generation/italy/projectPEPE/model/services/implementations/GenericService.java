package org.generation.italy.projectPEPE.model.services.implementations;

import org.generation.italy.projectPEPE.model.abstractions.AbstractFoodStorageRepository;
import org.generation.italy.projectPEPE.model.abstractions.AbstractRecipeRepository;
import org.generation.italy.projectPEPE.model.entities.Food;
import org.generation.italy.projectPEPE.model.entities.FoodStorage;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.entities.enums.Dish;
import org.generation.italy.projectPEPE.model.services.abstractions.AbstractGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class GenericService implements AbstractGenericService {

    private AbstractFoodStorageRepository foodStorageRepo;
    private AbstractRecipeRepository recipeRepo;
    private AbstractIngredientRepository ingredientRepo;
    private AbstractPersonRepository personRepo;

    @Autowired
    public GenericService(AbstractFoodStorageRepository foodStorageRepo, AbstractRecipeRepository recipeRepo,
                          AbstractIngredientRepository ingredientRepo) {
    public GenericService(AbstractFoodStorageRepository foodStorageRepo, AbstractRecipeRepository recipeRepo,
                          AbstractPersonRepository personRepo) {
        this.foodStorageRepo = foodStorageRepo;
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
        this.personRepo = personRepo;
    }

    @Override
    public Optional<Recipe> findRecipeById(long id) {
        return recipeRepo.findById(id);
    }

    @Override
    public Optional<Person> findPersonById(long id) {
        return personRepo.findById(id);
    }

    @Override
    public Iterable<FoodStorage> findByPersonAndFood(Person person, Food food) {
        return foodStorageRepo.findByPersonAndFood(person,food);
    }

    @Override
    public Iterable<Recipe> findByNameContains(String name) {
        return recipeRepo.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Iterable<Recipe> findByDiet(Diet diet) {
        return recipeRepo.findByDiet(diet);
    }

    @Override
    public Iterable<Recipe> findByDish(Dish dish) {
        return recipeRepo.findByDish(dish);
    }

    @Override
    public Iterable<Recipe> findByPersonDiet(Person person) {
        return recipeRepo.findByPersonDiet(person);
    }
    @Override
   public Iterable<Recipe> findByAvoidingIngredients(Person person) {
        return recipeRepo.findByAvoidingIngredients(person);
    }
   // @Override
  // public Iterable<Recipe> findByAvoidingIngredients(long id) {
   // return recipeRepo.findByAvoidingIngredients(id);
//}

    @Override
    public Iterable<Recipe> findByDifficulty(Difficulty difficulty) {
        return recipeRepo.findByDifficulty(difficulty);
    }

    @Override
    public Iterable<Recipe> findByToCook(boolean toCook) {
        return recipeRepo.findByToCook(toCook);
    }

    @Override
    public Iterable<Recipe> findAll() {
        return recipeRepo.findAll();
    }

}
