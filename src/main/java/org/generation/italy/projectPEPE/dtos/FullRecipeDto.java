package org.generation.italy.projectPEPE.dtos;

import org.generation.italy.projectPEPE.model.entities.Ingredient;
import org.generation.italy.projectPEPE.model.entities.Recipe;
import org.generation.italy.projectPEPE.model.entities.enums.AvgCost;

import java.util.Set;
import java.util.stream.Collectors;

public class FullRecipeDto {
    private long id;
    private String name;
    private String description;
    private String toCook;
    private String imageFilePath;
    private double totNutritionalValue;
    private int totPreparationTime;
    private String difficulty;
    private String diet;
    private String dish;
    private String totalCost; // ?????????
    private Set<String> ingredients;

//    public FullRecipeDto(long id, String name, String description, String toCook, String imageFilePath, double totNutritionalValue,
//                         int totPreparationTime, String difficulty, String diet, String dish) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.toCook = toCook;
//        this.imageFilePath = imageFilePath;
//        this.totNutritionalValue = totNutritionalValue;
//        this.totPreparationTime = totPreparationTime;
//        this.totalCost = AvgCost.EXOTIC;
//        this.difficulty = difficulty;
//        this.diet = diet;
//        this.dish = dish;
//    }

    public FullRecipeDto(long id, String name, String description, String toCook, String imageFilePath, double totNutritionalValue,
                         int totPreparationTime, String difficulty, String diet, String dish, String totalCost,
                         Set<String> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.toCook = toCook;
        this.imageFilePath = imageFilePath;
        this.totNutritionalValue = totNutritionalValue;
        this.totPreparationTime = totPreparationTime;
        this.difficulty = difficulty;
        this.diet = diet;
        this.dish = dish;
        this.totalCost = totalCost;
        this.ingredients = ingredients;
    }

    public static FullRecipeDto fromEntity(Recipe recipe){
        System.out.println("AAAAAAAAAAAAAAAAAA " + checkIsToCook(recipe.getToCook()));
        return new FullRecipeDto(recipe.getId(), recipe.getName(), recipe.getDescription(), checkIsToCook(recipe.getToCook()),
                recipe.getImageFilePath(), recipe.getTotNutritionalValue(), recipe.getTotPreparationTime(),
                recipe.getDifficulty().getDifficultyName(), recipe.getDiet().getDietName(), recipe.getDish().getDishName(),
                calculateAvgCost(recipe.getIngredients()), getIngredientsName(recipe.getIngredients()));
    }

    private static Set<String> getIngredientsName(Set<Ingredient> ingredients){
        return ingredients.stream().map(e -> e.getFood().getName()).collect(Collectors.toSet());
    }

    private static String calculateAvgCost(Set<Ingredient> ingredients){
        double avg;
        int total = 0;
        for(Ingredient f : ingredients){
            if(f.getFood().getAvgCost().equals(AvgCost.EXOTIC)){
                return AvgCost.EXOTIC.toString();
            }
            total += f.getFood().getAvgCost().getCostLevel();
        }
        avg = total / ingredients.size();

        return AvgCost.getEnumByValue(avg).toString();
    }

    private static String checkIsToCook(boolean toCook){
        return toCook ? "Sì" : "No";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
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

    public String getToCook() {
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

    public String getTotalCost() {
        if(totalCost.equals("LOW")){
            return "basso";
        } if(totalCost.equals("MEDIUM")){
            return "medio";
        } if(totalCost.equals("HIGH")){
            return "alto";
        } if(totalCost.equals("EXOTIC")){
            return "esotico";
        }
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
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
