package org.generation.italy.ProjectPEPE.model.entities;

import org.generation.italy.ProjectPEPE.model.entities.enums.Diet;
import org.generation.italy.ProjectPEPE.model.entities.enums.PhisicalActivity;
import org.generation.italy.ProjectPEPE.model.entities.enums.Sex;
import org.generation.italy.ProjectPEPE.model.entities.enums.Work;

import java.util.Map;
import java.util.Set;

public class Person {
    //dati generali user
    private long id;
    private String firstname;
    private String lastname;
    private int age;
    private int weight;
    private int height;
    //enum
    private Sex sex;
    private Work work;
    private Diet diet;
    private PhisicalActivity phisicalActivity;
    //da calcolare noi
    private int idealWeight;
    private int calorieReq;
    //dati accesso
    private String mail;
    private String password;
    private Set<Food> avoidIngredients;
    private Map<Long,Integer> foodStorage;

    public Person(){

    }

    public Person(long id, String firstname, String lastname, int age, int weight,
                  int height, Sex sex, Work work, Diet diet, PhisicalActivity phisicalActivity,
                  int idealWeight, int calorieReq, String mail, String password,
                  Set<Food> avoidIngredients, Map<Long, Integer> foodStorage) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.work = work;
        this.diet = diet;
        this.phisicalActivity = phisicalActivity;
        this.idealWeight = idealWeight;
        this.calorieReq = calorieReq;
        this.mail = mail;
        this.password = password;
        this.avoidIngredients = avoidIngredients;
        this.foodStorage = foodStorage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public PhisicalActivity getPhisicalActivity() {
        return phisicalActivity;
    }

    public void setPhisicalActivity(PhisicalActivity phisicalActivity) {
        this.phisicalActivity = phisicalActivity;
    }

    public int getIdealWeight() {
        return idealWeight;
    }

    public void setIdealWeight(int idealWeight) {
        this.idealWeight = idealWeight;
    }

    public int getCalorieReq() {
        return calorieReq;
    }

    public void setCalorieReq(int calorieReq) {
        this.calorieReq = calorieReq;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Food> getAvoidIngredients() {
        return avoidIngredients;
    }

    public void setAvoidIngredients(Set<Food> avoidIngredients) {
        this.avoidIngredients = avoidIngredients;
    }

    public Map<Long, Integer> getFoodStorage() {
        return foodStorage;
    }

    public void setFoodStorage(Map<Long, Integer> foodStorage) {
        this.foodStorage = foodStorage;
    }
}
