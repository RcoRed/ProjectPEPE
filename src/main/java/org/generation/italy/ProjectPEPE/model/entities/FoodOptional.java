package org.generation.italy.ProjectPEPE.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_food")
public class FoodOptional {
    @ManyToOne
    @Column(name = "id_recipe")
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;

    //id uguale al food
    @OneToOne
    @Column(name = "id_recipe")
    @JoinColumn(name = "id_food")
    private Food food;
    private int quantity;

    @Column(name = "is_optional")
    private boolean isOptional;

    public FoodOptional() {
    }

    public FoodOptional(Food food, int quantity, boolean isOptional) {
        this.food = food;
        this.quantity = quantity;
        this.isOptional = isOptional;
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
