package org.generation.italy.projectPEPE.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @Column(name = "id_ingredient")
    @GeneratedValue(generator = "ingredient_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ingredient_generator", sequenceName = "ingredient_sequence", allocationSize = 1)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;

    //id uguale al food
    @ManyToOne
    @JoinColumn(name = "id_food")
    private Food food;
    private Integer weight;
    private Double quantity;

    @Column(name = "is_optional")
    private boolean isOptional;

    public Ingredient() {
    }

    public Ingredient(long id, Recipe recipe, Food food, Integer weight, Double quantity, boolean isOptional) {
        this.id = id;
        this.recipe = recipe;
        this.food = food;
        this.weight = weight;
        this.quantity = quantity;
        this.isOptional = isOptional;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantity() {
        return quantity;
    }
}
