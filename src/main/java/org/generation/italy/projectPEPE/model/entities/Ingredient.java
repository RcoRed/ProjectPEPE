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
    private int quantity;

    @Column(name = "is_optional")
    private boolean isOptional;

    public Ingredient() {
    }

    public Ingredient(long id, Recipe recipe, Food food, int quantity, boolean isOptional) {
        this.id = id;
        this.recipe = recipe;
        this.food = food;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

}
