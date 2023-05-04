package org.generation.italy.projectPEPE.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import org.generation.italy.projectPEPE.model.entities.enums.AvgCost;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.Difficulty;
import org.generation.italy.projectPEPE.model.entities.enums.Dish;
import org.hibernate.annotations.Type;

import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @Column(name = "id_recipe")
    @GeneratedValue(generator = "recipe_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "recipe_generator", sequenceName = "recipe_sequence", allocationSize = 1)
    private long id;

    private String name;
    private String description;

    @Column(name = "to_cook", columnDefinition = "BIT")
    private boolean toCook;

    @Column(name = "image_file_path")
    private String imageFilePath;

    //totali
    @Column(name = "tot_nutritional_value")
    private double totNutritionalValue;

    @Column(name = "tot_preparation_time")
    private int totPreparationTime;

//    @Column(name = "number_ingredient")
//    private int numberIngredient;

    //enum
    @Column(name = "total_cost", columnDefinition = "AVG_COST")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private AvgCost totalCost;

    @Column(columnDefinition = "DIFFICULTY")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Difficulty difficulty;

    @Column(columnDefinition = "DIET")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Diet diet;

    @Column(columnDefinition = "DISH")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Dish dish;

//    è un set!!!
    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;

    public Recipe() {
    }
//    public Recipe(long id, String name, String description, boolean toCook,
//                  String imageFilePath, int totPreparationTime, Difficulty difficulty,
//                  Diet diet, Dish dish) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.toCook = toCook;
//        this.imageFilePath = imageFilePath;
//        this.totPreparationTime = totPreparationTime;
//        this.difficulty = difficulty;
//        this.diet = diet;
//        this.dish = dish;
//        this.totalCost = AvgCost.EXOTIC;
//        this.totNutritionalValue = 0;
//        //this.numberIngredient = 0;
//    }

    public Recipe(long id, String name, String description, boolean toCook,
                  String imageFilePath, int totPreparationTime, Difficulty difficulty,
                  Diet diet, Dish dish, Set<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.toCook = toCook;
        this.imageFilePath = imageFilePath;
        this.totPreparationTime = totPreparationTime;
        this.difficulty = difficulty;
        this.diet = diet;
        this.dish = dish;
        this.ingredients = ingredients;
        this.totalCost = this.calculateAvgCost();
        this.totNutritionalValue = this.calculateTotNutritionalValue();
//        this.numberIngredient = this.calculateNumberIngredient();
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
        avg = (float)total / ingredients.size();

        return AvgCost.getEnumByValue(avg);
    }

    private double calculateTotNutritionalValue(){
        return ingredients.stream().mapToDouble(i -> i.getFood().getKal()).sum();
    }

//    private int calculateNumberIngredient(){
//        return ingredients.size();
//    }

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

    public boolean getToCook() {
        return toCook;
    }

    public void setToCook(boolean toCook) {
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

//    public int getNumberIngredient() {
//        return numberIngredient;
//    }
//
//    public void setNumberIngredient(int numberIngredient) {
//        this.numberIngredient = numberIngredient;
//    }

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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


}
