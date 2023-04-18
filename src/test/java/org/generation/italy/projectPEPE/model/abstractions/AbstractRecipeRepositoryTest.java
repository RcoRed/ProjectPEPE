package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.*;
import org.generation.italy.projectPEPE.model.entities.enums.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.generation.italy.projectPEPE.testConstants.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AbstractRecipeRepositoryTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private AbstractRecipeRepository repo;

    private Recipe recipe1;
    private Food food1;
    private Food food2;
    private Set<Food> foods = new HashSet<>();
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Person person1;


    @BeforeEach
    void setUp() {
        food1 = new Food(10000,FOOD_NAME1,null, AvgCost.MEDIUM,0,0,0,0,0,0,0);
        food2= new Food(10003,FOOD_NAME2,null,AvgCost.EXOTIC,0,0,0,0,0,0,0);
        foods.add(food1);
        foods.add(food2);
        System.out.println(foods);
        person1 = new Person(10000,FIRSTNAME1,LASTNAME1,DOB1,WEIGHT1,HEIGHT1,SEX1,
                Work.ACTIVE,Diet.VEGAN,PhysicalActivity.HIGH,MAIL1,PASSWORD1,foods,null);
        System.out.println(person1);
//        food1 = em.persist(food1);
//        food2 = em.persist(food2);
//        ingredient1= new Ingredient(0,recipe1,food1,100,false);
//        ingredient2= new Ingredient(0,recipe1,food2,200,true);
//        foods.add(ingredient1);
//        foods.add(ingredient2);
//        recipe1 = new Recipe(0,RECIPE_NAME1,RECIPE_DESCRIPTION1,true,
//                IMAGE_FILE_PATH1,TOT_PREPARATION_TIME1, Difficulty.MEDIUM, Diet.VEGAN, Dish.FIRST, foods);
//        System.out.println(foods);
//        System.out.println("dopo recipe");
        //em.persist(recipe1);
        //em.persist(ingredient1);
        //em.persist(ingredient2);
       // em.flush();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByNameContains_Should_Find_Recipe_By_Title_Like() {

        Iterable<Recipe> recipeIterable = repo.findByNameContains("patat");
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(1,result.size());
        assertEquals("Recipe di prova1 patate lesse",result.get(0).getName());

    }

    @Test
    void findByDiet_Should_Find_Recipe_By_Diet() {
        Iterable<Recipe> recipeIterable = repo.findByDiet(Diet.VEGAN);
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(2,result.size());
        assertEquals(Diet.VEGAN,result.get(0).getDiet());
        assertEquals(Diet.VEGAN,result.get(1).getDiet());

    }

    @Test
    void findByPersonDiet_Should_Find_Recipe_By_Person_Diet() {
        Iterable<Recipe> recipeIterable = repo.findByPersonDiet(person1);
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(2,result.size());
        assertEquals(Diet.VEGAN,result.get(0).getDiet());
        assertEquals(Diet.VEGAN,result.get(1).getDiet());
    }

    @Test
    void findByAvoidingIngredients() {
        Iterable<Recipe> recipeIterable = repo.findByAvoidingIngredients(person1);
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(1,result.size());
        assertEquals(10001,result.get(0).getId());
    }

    @Test
    void findByDifficulty_Should_Find_Recipe_By_Difficulty() {
        Iterable<Recipe> recipeIterable = repo.findByDifficulty(Difficulty.MEDIUM);
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(1,result.size());
        assertEquals(Difficulty.MEDIUM,result.get(0).getDifficulty());
    }

    @Test
    void findByToCook_Should_Find_Recipe_By_Food_To_Cook() {
        Iterable<Recipe> recipeIterable = repo.findByToCook(true);
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(1,result.size());
        assertTrue(result.get(0).isToCook());
    }
}