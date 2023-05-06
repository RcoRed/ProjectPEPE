package org.generation.italy.projectPEPE.model.abstractions;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.entities.enums.Dish;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public class AbstractRecipeRepositoryCustomImpl implements AbstractRecipeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Recipe> findRecipeByFilters(Diet diet, Difficulty difficulty, Boolean isToCook, String name, Dish dish, Long idPerson, Integer nPage, Integer nRecipes) {
        StringBuilder queryPart = new StringBuilder("from Recipe r ");
        boolean isFirstCondition = true;
        if(idPerson != null){
            isFirstCondition = false;
            queryPart.append("where r.id NOT IN " +
                    "(SELECT i.recipe FROM AvoidingFood af JOIN af.person p JOIN Ingredient i ON af.food = i.food WHERE p.id = ").append(idPerson).append(")");
        }

        if (dish != null) {
            if (isFirstCondition) {
                isFirstCondition = false;
                queryPart.append(" where ");
            } else {
                queryPart.append(" and ");
            }
            queryPart.append("r.dish = '").append(dish.toString()).append("'");
        }

        if (diet != null) {
            if (isFirstCondition) {
                isFirstCondition = false;
                queryPart.append(" where ");
            } else {
                queryPart.append(" and ");
            }
            queryPart.append("r.diet = '").append(diet.toString()).append("'");
        }

        if (difficulty != null) {
            if (isFirstCondition) {
                isFirstCondition = false;
                queryPart.append(" where ");
            } else{
                queryPart.append(" and ");
            }
            queryPart.append("r.difficulty = '").append(difficulty.toString()).append("'");
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

        System.out.println("nome: " + name);
        if (name != null){
            if(isFirstCondition){
                isFirstCondition = false;
                queryPart.append(" where ");
            } else{
                queryPart.append(" and ");
            }
            queryPart.append("lower(r.name) like '%").append(name.toLowerCase()).append("%'");
        }

        System.out.println(queryPart);
        TypedQuery<Recipe> query = entityManager.createQuery(queryPart.toString(), Recipe.class);

        if (nPage != null && nRecipes != null){
            query.setFirstResult(nPage * nRecipes);
            query.setMaxResults(nRecipes);
        }
        return query.getResultList();
    }
}
