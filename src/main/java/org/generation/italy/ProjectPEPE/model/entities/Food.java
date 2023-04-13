package org.generation.italy.ProjectPEPE.model.entities;

import org.generation.italy.ProjectPEPE.model.entities.enums.AvgCost;
import org.generation.italy.ProjectPEPE.model.entities.enums.Category;

import java.util.Set;

public class Food {
    private long id;
    private String name;
    private Set<Category> categoryList;
    private AvgCost avgCost;
    private double kal;
    private double carbs;
    private double sugar;
    private double protein;
    private double fat;
    private double fiber;
    private double saturatedFat;

    public Food() {
    }

    public Food(long id, String name, Set<Category> categoryList, AvgCost avgCost, double kal,
                double carbs, double sugar, double protein, double fat, double fiber,
                double saturatedFat) {
        this.id = id;
        this.name = name;
        this.categoryList = categoryList;
        this.avgCost = avgCost;
        this.kal = kal;
        this.carbs = carbs;
        this.sugar = sugar;
        this.protein = protein;
        this.fat = fat;
        this.fiber = fiber;
        this.saturatedFat = saturatedFat;
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

    public Set<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(Set<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public AvgCost getAvgCost() {
        return avgCost;
    }

    public void setAvgCost(AvgCost avgCost) {
        this.avgCost = avgCost;
    }

    public double getKal() {
        return kal;
    }

    public void setKal(double kal) {
        this.kal = kal;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getFiber() {
        return fiber;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }
}
