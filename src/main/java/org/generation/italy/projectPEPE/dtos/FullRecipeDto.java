package org.generation.italy.projectPEPE.dtos;

import org.generation.italy.projectPEPE.model.entities.Ingredient;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.AvgCost;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.entities.enums.Dish;

import java.util.Set;

public class FullRecipeDto {
    private long id;
    private String name;
    private String description;
    private String toCook;
    private String imageFilePath;
    private double totNutritionalValue;
    private int totPreparationTime;
    private AvgCost totalCost;
    private String difficulty;
    private String diet;
    private String dish;
    private Set<Ingredient> ingredients;

    public FullRecipeDto(long id, String name, String description, String toCook, String imageFilePath, double totNutritionalValue,
                         int totPreparationTime, String difficulty, String diet, String dish) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.toCook = toCook;
        this.imageFilePath = imageFilePath;
        this.totNutritionalValue = totNutritionalValue;
        this.totPreparationTime = totPreparationTime;
        this.totalCost = AvgCost.EXOTIC;
        this.difficulty = difficulty;
        this.diet = diet;
        this.dish = dish;
    }

    public FullRecipeDto(long id, String name, String description, String toCook, String imageFilePath, double totNutritionalValue,
                         int totPreparationTime, String difficulty, String diet, String dish,
                         Set<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.toCook = toCook;
        this.imageFilePath = imageFilePath;
        this.totNutritionalValue = totNutritionalValue;
        this.totPreparationTime = totPreparationTime;
        this.totalCost = this.calculateAvgCost();
        this.difficulty = difficulty;
        this.diet = diet;
        this.dish = dish;
        this.ingredients = ingredients;
    }

    public static FullRecipeDto fromEntity(Recipe recipe){
        return new FullRecipeDto(recipe.getId(),recipe.getName(),recipe.getDescription(), isToCook(recipe.isToCook()),
                recipe.getImageFilePath(), recipe.getTotNutritionalValue(), recipe.getTotPreparationTime(),
                recipe.getDifficulty().getDifficultyName(), recipe.getDiet().getDietName(), recipe.getDish().getDishName());
    }

    private AvgCost calculateAvgCost(){
        double avg;
        int total = 0;
        for(Ingredient f : ingredients){
            if(f.getFood().getAvgCost().equals(AvgCost.EXOTIC)){
                return AvgCost.EXOTIC;
            }
            total += f.getFood().getAvgCost().getCostLevel();
        }
        avg = total / ingredients.size();

        return AvgCost.getEnumByValue(avg);
    }

    private static String isToCook(boolean toCook){
        return toCook ? "Sì" : "No";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public String isToCook() {
        return toCook;
    }

    public void setToCook(String toCook) {
        this.toCook = toCook;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public double getTotNutritionalValue() {
        return totNutritionalValue;
    }

    public void setTotNutritionalValue(double totNutritionalValue) {
        this.totNutritionalValue = totNutritionalValue;
    }

    public int getTotPreparationTime() {
        return totPreparationTime;
    }

    public void setTotPreparationTime(int totPreparationTime) {
        this.totPreparationTime = totPreparationTime;
    }

    public AvgCost getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(AvgCost totalCost) {
        this.totalCost = totalCost;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }


}
