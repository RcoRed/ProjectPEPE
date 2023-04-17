package org.generation.italy.projectPEPE.model.abstractions;

import org.generation.italy.projectPEPE.model.entities.Food;
import org.generation.italy.projectPEPE.model.entities.FoodStorage;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import static org.generation.italy.projectPEPE.testConstants.Constants.*;

//rollBack automatica
@DataJpaTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class AbstractFoodStorageRepositoryTest {
    @Autowired
    private TestEntityManager em;
    @Autowired
    private AbstractFoodStorageRepository repo;
    private Person p1;
    private Food f1;
    private FoodStorage fS1;
    private FoodStorage fS2;


    @BeforeEach
    void setUp() {
        //p1 = new Person(0,FIRSTNAME1,LASTNAME1,DOB1,WEIGHT1,HEIGHT1,SEX1,CALORIES1,MAIL1,PASSWORD1);
        f1 = new Food(0,FOOD_NAME1,null,null,0,0,0,0,0,0,0);
        fS1 = new FoodStorage(0,f1,p1,QUANTITY1);
        fS2 = new FoodStorage(0,f1,p1,QUANTITY2);

        em.persist(p1);
        em.persist(f1);
        em.persist(fS1);
        em.persist(fS2);
        em.flush();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByPersonAndFood_should_find_foodStorage_when_present() {
        Iterable<FoodStorage> foodStorageIterable = repo.findByPersonAndFood(p1,f1);
        List<FoodStorage> result = new ArrayList<>();
        foodStorageIterable.iterator().forEachRemaining(result::add);
        assertEquals(2,result.size());
    }
}