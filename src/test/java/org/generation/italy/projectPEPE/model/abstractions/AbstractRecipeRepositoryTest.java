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
    private Recipe recipe2;
    private Recipe recipe3;
    private Food food1;
    private Food food2;
    private Set<Food> foods = new HashSet<>();
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;
    private Ingredient ingredient4;
    private Person person1;
    private Person person2;
    private AvoidingFood avoidingFood1;
    private AvoidingFood avoidingFood2;

    @BeforeEach
    void setUp() {
        recipe1 = new Recipe(0,RECIPE_NAME1,RECIPE_DESCRIPTION1,true,
                IMAGE_FILE_PATH1,2,Difficulty.MEDIUM,Diet.VEGETARIAN,Dish.FIRST);
        em.persist(recipe1);
        recipe2 = new Recipe(0,RECIPE_NAME2,RECIPE_DESCRIPTION1,true,
                IMAGE_FILE_PATH1,2,Difficulty.LOW,Diet.VEGAN,Dish.FIRST);
        em.persist(recipe2);
        recipe3 = new Recipe(0,RECIPE_NAME_NOT_PRESENT,RECIPE_DESCRIPTION1,false,
                IMAGE_FILE_PATH1,2,Difficulty.MEDIUM,Diet.VEGAN,Dish.FIRST);
        em.persist(recipe3);
        person1 = new Person(0,FIRSTNAME1,LASTNAME1,DOB1,WEIGHT1,HEIGHT1,SEX1,
            Work.ACTIVE,Diet.VEGAN,PhysicalActivity.HIGH,MAIL1,PASSWORD1);
        em.persist(person1);
        person2 = new Person(0,FIRSTNAME1,LASTNAME1,DOB1,WEIGHT1,HEIGHT1,SEX1,
                Work.ACTIVE,Diet.VEGAN,PhysicalActivity.HIGH,MAIL1,PASSWORD1);
        em.persist(person2);
        food1 = new Food(0,FOOD_NAME1,null, AvgCost.MEDIUM,0,0,0,0,0,0,0);
        em.persist(food1);
        food2 = new Food(0,FOOD_NAME2,null,AvgCost.EXOTIC,0,0,0,0,0,0,0);
        em.persist(food2);

        ingredient1 = new Ingredient(0,recipe1,food1,100, null, true);
        em.persist(ingredient1);
        ingredient2 = new Ingredient(0,recipe2,food2,100, null, true);
        em.persist(ingredient2);
        ingredient3 = new Ingredient(0,recipe3,food1,100, null, true);
        em.persist(ingredient3);
        ingredient4 = new Ingredient(0,recipe1,food2,100, null, true);
        em.persist(ingredient4);

        avoidingFood1 = new AvoidingFood(0,person1,food1);
        em.persist(avoidingFood1);
        avoidingFood2 =new AvoidingFood(0,person2,food2);
        em.persist(avoidingFood2);
        em.flush();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByNameContains_Should_Find_Recipe_By_Title_Like() {
        Iterable<Recipe> recipeIterable = repo.findByNameContains("meRe");
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(2,result.size());
        assertTrue(result.get(0).getName().equals(RECIPE_NAME1) || result.get(1).getName().equals(RECIPE_NAME1));
        assertTrue(result.get(0).getName().equals(RECIPE_NAME2) || result.get(1).getName().equals(RECIPE_NAME2));
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
        assertEquals(recipe2.getId(),result.get(0).getId());
    }

    @Test
    void findByDifficulty_Should_Find_Recipe_By_Difficulty() {
        Iterable<Recipe> recipeIterable = repo.findByDifficulty(Difficulty.MEDIUM);
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(2,result.size());
        assertEquals(Difficulty.MEDIUM,result.get(0).getDifficulty());
        assertEquals(Difficulty.MEDIUM,result.get(1).getDifficulty());
    }

    @Test
    void findByToCook_Should_Find_Recipe_By_Food_To_Cook_True() {
        Iterable<Recipe> recipeIterable = repo.findByToCook(true);
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(2,result.size());
        assertTrue(result.get(0).isToCook());
        assertTrue(result.get(1).isToCook());
    }

    @Test
    void findByToCook_Should_Find_Recipe_By_Food_To_Cook_False() {
        Iterable<Recipe> recipeIterable = repo.findByToCook(false);
        List<Recipe> result = new ArrayList<>();
        recipeIterable.iterator().forEachRemaining(result::add);
        assertEquals(1,result.size());
        assertFalse(result.get(0).isToCook());
    }
}