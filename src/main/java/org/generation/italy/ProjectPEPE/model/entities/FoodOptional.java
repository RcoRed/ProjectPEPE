package org.generation.italy.ProjectPEPE.model.entities;

public class FoodOptional {
    //id uguale al food
    private long id;
    private int quantity;
    private boolean isOptional;

    public FoodOptional() {
    }

    public FoodOptional(long id, int quantity, boolean isOptional) {
        this.id = id;
        this.quantity = quantity;
        this.isOptional = isOptional;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
