package org.generation.italy.projectPEPE.model.entities.enums;

public enum Diet {
    VEGAN("vegana"),VEGETARIAN("vegetariana"),OMNIVOROUS("onnivora");

    private String dietName;

    Diet(String dietName) {
        this.dietName = dietName;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }
}
