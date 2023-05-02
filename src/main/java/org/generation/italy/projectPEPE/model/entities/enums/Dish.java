package org.generation.italy.projectPEPE.model.entities.enums;

public enum Dish {
    APPETIZER("antipasto"),FIRST("primo"),SECOND("secondo"),DESSERT("dessert");

    private String dishName;

    Dish(String dishName) {
        this.dishName = dishName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
