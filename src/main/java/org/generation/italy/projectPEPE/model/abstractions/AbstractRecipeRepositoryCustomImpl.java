package org.generation.italy.projectPEPE.model.abstractions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public class AbstractRecipeRepositoryCustomImpl implements AbstractRecipeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Recipe> findRecipeByFilters(Diet diet, Difficulty difficulty, Boolean isToCook, String name) {
        StringBuilder queryPart = new StringBuilder("from Recipe r");
        boolean isFirstCondition = true;
        if (diet != null) {
            if (isFirstCondition) {
                isFirstCondition = false;
                queryPart.append(" where");
            }
            if (diet.equals(Diet.VEGAN)) {
                queryPart.append(" r.diet = 'VEGAN'");
            } else if (diet.equals(Diet.VEGETARIAN)) {
                queryPart.append(" r.diet = 'VEGETARIAN'");
            } else if (diet.equals(Diet.OMNIVOROUS)) {
                queryPart.append(" r.diet = 'OMNIVOROUS'");
            }
        }

        if (difficulty != null) {
            if (isFirstCondition) {
                isFirstCondition = false;
                queryPart.append(" where ");
            } else{
                queryPart.append(" and ");
            }
            if (difficulty.equals(Difficulty.LOW)) {
                queryPart.append(" r.difficulty = 'LOW'");
            } else if (difficulty.equals(Difficulty.MEDIUM)) {
                queryPart.append(" r.difficulty = 'MEDIUM'");
            } else if (difficulty.equals(Difficulty.HIGH)) {
                queryPart.append(" r.difficulty = 'HIGH'");
            }
        }

        if (isToCook != null) {
            if (isFirstCondition) {
                isFirstCondition = false;
                queryPart.append(" where ");
            } else{
                queryPart.append(" and ");
            }
            if (isToCook) {
                queryPart.append("r.toCook = true");
            } else {
                queryPart.append("r.toCook = false");
            }
        }

        if (name != null){
            if(isFirstCondition){
                isFirstCondition = false;
                queryPart.append(" where ");
            } else{
                queryPart.append(" and ");
            }
            queryPart.append("r.name like %").append(name).append("%");
        }
        TypedQuery<Recipe> query = entityManager.createQuery(queryPart.toString(), Recipe.class);
        return query.getResultList();
    }
}
