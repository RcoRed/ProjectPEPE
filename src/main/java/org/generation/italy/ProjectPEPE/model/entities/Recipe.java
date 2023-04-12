package org.generation.italy.ProjectPEPE.model.entities;

import org.generation.italy.ProjectPEPE.model.entities.enums.AvgCost;
import org.generation.italy.ProjectPEPE.model.entities.enums.Diet;
import org.generation.italy.ProjectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.ProjectPEPE.model.entities.enums.Dish;

import java.util.Set;

public class Recipe {
    private long id;
    private String name;
    private String description;
    private boolean toCook;
    //totali
    private int totNutritionalValue;
    private int totPreparationTime;
    private int numberIngredient;
    //enum
    private AvgCost totalCost;
    private Difficulty difficulty;
    private Diet diet;
    private Dish dish;
    //è un set!!!
    private Set<Food> ingredientList;

    public Recipe() {
    }

    public Recipe(long id, String name, String description, boolean toCook, int totNutritionalValue,
                  int totPreparationTime, int numberIngredient, AvgCost totalCost, Difficulty difficulty,
                  Diet diet, Dish dish, Set<Food> ingredientList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.toCook = toCook;
        this.totNutritionalValue = totNutritionalValue;
        this.totPreparationTime = totPreparationTime;
        this.numberIngredient = numberIngredient;
        this.totalCost = totalCost;
        this.difficulty = difficulty;
        this.diet = diet;
        this.dish = dish;
        this.ingredientList = ingredientList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isToCook() {
        return toCook;
    }

    public void setToCook(boolean toCook) {
        this.toCook = toCook;
    }

    public int getTotNutritionalValue() {
        return totNutritionalValue;
    }

    public void setTotNutritionalValue(int totNutritionalValue) {
        this.totNutritionalValue = totNutritionalValue;
    }

    public int getTotPreparationTime() {
        return totPreparationTime;
    }

    public void setTotPreparationTime(int totPreparationTime) {
        this.totPreparationTime = totPreparationTime;
    }

    public int getNumberIngredient() {
        return numberIngredient;
    }

    public void setNumberIngredient(int numberIngredient) {
        this.numberIngredient = numberIngredient;
    }

    public AvgCost getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(AvgCost totalCost) {
        this.totalCost = totalCost;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Set<Food> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(Set<Food> ingredientList) {
        this.ingredientList = ingredientList;
    }
}
