package org.generation.italy.projectPEPE.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.PhysicalActivity;
import org.generation.italy.projectPEPE.model.entities.enums.Sex;
import org.generation.italy.projectPEPE.model.entities.enums.Work;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    //dati generali user
    @Id
    @Column(name = "id_person")
    @GeneratedValue(generator = "person_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "person_generator", sequenceName = "person_sequence", allocationSize = 1)
    private long id;

    private String firstname;
    private String lastname;
    private LocalDate dob;
    private int weight;
    private int height;

    //enum
    @Column(columnDefinition = "SEX")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Sex sex;

    @Column(columnDefinition = "WORK")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Work work;

    @Column(columnDefinition = "DIET")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Diet diet;

    @Column(name = "physical_activity", columnDefinition = "PHYSICAL_ACTIVITY")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private PhysicalActivity physicalActivity;
    //da calcolare noi
    @Column(name = "ideal_weight")
    private double idealWeight;

    @Column(name = "calorie_req")
    private int calorieReq;

    //dati accesso
    private String mail;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "avoiding_food",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_food"))
    private Set<Food> avoidFoods;

    @OneToMany
    private Set<FoodStorage> foodStorages;

    public Person(){

    }

    public Person(long id, String firstname, String lastname, LocalDate dob, int weight,
                  int height, Sex sex, Work work, Diet diet, PhysicalActivity physicalActivity, String mail, String password,
                  Set<Food> avoidFoods, Set<FoodStorage> foodStorages) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.work = work;
        this.diet = diet;
        this.physicalActivity = physicalActivity;
        this.mail = mail;
        this.password = password;
        this.avoidFoods = avoidFoods;
        this.foodStorages = foodStorages;
        this.idealWeight = calculateIdealWeight();
        this.calorieReq = calculateCalorieReq();
        //da aggiungere calcolo calorieReq e idealWeight
    }

    private double calculateIdealWeight(){
        return  getSex() == Sex.MALE? (Math.pow(getHeight(),2)/100)*22.1/100 : (Math.pow(getHeight(),2)/100)*20.6/100;
        //return  sex == Sex.MALE? ((getHeight()*getHeight())/100)*22.1/100 : ((getHeight()*getHeight())/100)*20.6/100;
    }

    private int calculateBasalMetabolicRateMinor(){
        if(getPhysicalActivity() == PhysicalActivity.LOW){
            return (int) getIdealWeight()*31;
        }
        if(getPhysicalActivity() == PhysicalActivity.NORMAL){
            return (int) getIdealWeight()*38;
        }
        return (int) getIdealWeight()*44;
    }
    private int calculateBasalMetabolicRate(){
        int age = getAge();
        if (age<18){
            return calculateBasalMetabolicRateMinor();
        }
        if(age<=29){
            return getSex() == Sex.MALE? (int) (15.3*getIdealWeight()+679) : (int) (14.7*getIdealWeight()+496);
        }
        if(age<=59){
            return getSex() == Sex.MALE? (int) (11.6*getIdealWeight()+879) : (int) (8.7*getIdealWeight()+829);
        }
        if(age<=74){
            return getSex() == Sex.MALE? (int) (11.9*getIdealWeight()+700) : (int) (9.2*getIdealWeight()+688);
        }
        return getSex() == Sex.MALE? (int) (8.4*getIdealWeight()+819) : (int) (9.8*getIdealWeight()+624);
    }

    private double calculateLaf(){
        double tot = getPhysicalActivity().getPAValue()+getWork().getWorkValue();
        double avg = tot/2;
        int age = getAge();
        if(age>=18 && age<=59){
            if (avg<2){
                return getSex() == Sex.MALE? 1.55 : 1.56;
            }
            if (avg<3){
                return getSex() == Sex.MALE? 1.78 : 1.64;
            }
            return getSex() == Sex.MALE? 2.10 : 1.82;
        }
        return getSex() == Sex.MALE? 1.51 : 1.56;


    }
    //quello vero
    private int calculateCalorieReq(){
        int mb = calculateBasalMetabolicRate();
        if (getAge() < 18){
            return mb;
        }
        return (int) (calculateLaf()*mb);
    }

    private int getAge(){
        return LocalDate.now().getYear() - getDob().getYear();
    }

    public static void main(String[] args) {
        Person p = new Person(1,"nome","cognome",LocalDate.of(1988,1,1),61,190,Sex.MALE,Work.ACTIVE,Diet.OMNIVOROUS,
                PhysicalActivity.LOW,"mail","pass",null,null);
        System.out.println(p.getIdealWeight());
        System.out.println(p.getCalorieReq());
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public PhysicalActivity getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(PhysicalActivity physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

    public double getIdealWeight() {
        return idealWeight;
    }

    public void setIdealWeight(double idealWeight) {
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

    public Set<Food> getAvoidFoods() {
        return avoidFoods;
    }

    public void setAvoidFoods(Set<Food> avoidFoods) {
        this.avoidFoods = avoidFoods;
    }

    public Set<FoodStorage> getFoodStorages() {
        return foodStorages;
    }

    public void setFoodStorages(Set<FoodStorage> foodStorages) {
        this.foodStorages = foodStorages;
    }
}
