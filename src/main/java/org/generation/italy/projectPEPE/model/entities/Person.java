package org.generation.italy.projectPEPE.model.entities;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.generation.italy.projectPEPE.model.entities.enums.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
//@SuperBuilder
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id_person_auth")
@Table(name = "person")
public class Person extends PersonAuth{
    //dati generali user
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "avoiding_food",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_food"))
    private Set<Food> avoidFoods;

    @OneToMany
    private Set<FoodStorage> foodStorages;

    //costruttore senza i due Set
    public Person(long id, String mail, String password, Role role, String firstname, String lastname, LocalDate dob, int weight,
                  int height, Sex sex, Work work, Diet diet, PhysicalActivity physicalActivity) {
        super(id,mail,password,role,null);
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.work = work;
        this.diet = diet;
        this.physicalActivity = physicalActivity;
        this.idealWeight = calculateIdealWeight();
        this.calorieReq = calculateCalorieReq();
    }

    public Person(long id, String mail, String password, Role role, String firstname, String lastname, LocalDate dob, int weight,
                  int height, Sex sex, Work work, Diet diet, PhysicalActivity physicalActivity,
                  Set<Food> avoidFoods, Set<FoodStorage> foodStorages) {
        super(id,mail,password,role,null);
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.work = work;
        this.diet = diet;
        this.physicalActivity = physicalActivity;
        this.avoidFoods = avoidFoods;
        this.foodStorages = foodStorages;
        this.idealWeight = calculateIdealWeight();
        this.calorieReq = calculateCalorieReq();
    }

    private double calculateIdealWeight(){
        return getSex() == Sex.MALE? (Math.pow(getHeight(), 2) / 100) * 22.1 / 100 : (Math.pow(getHeight(), 2) / 100) * 20.6 / 100;
        //return  sex == Sex.MALE? ((getHeight()*getHeight())/100)*22.1/100 : ((getHeight()*getHeight())/100)*20.6/100;
    }

    private int calculateBasalMetabolicRateMinor(){
        if(getPhysicalActivity() == PhysicalActivity.LOW){
            return (int) getIdealWeight() * 31;
        }
        if(getPhysicalActivity() == PhysicalActivity.NORMAL){
            return (int) getIdealWeight() * 38;
        }
        return (int) getIdealWeight() * 44;
    }

    private int calculateBasalMetabolicRate(){
        int age = getAge();
        if (age < 18){
            return calculateBasalMetabolicRateMinor();
        }
        if(age <= 29){
            return getSex() == Sex.MALE? (int) (15.3 * getIdealWeight() + 679) : (int) (14.7 * getIdealWeight() + 496);
        }
        if(age <= 59){
            return getSex() == Sex.MALE? (int) (11.6 * getIdealWeight() + 879) : (int) (8.7 * getIdealWeight() + 829);
        }
        if(age <= 74){
            return getSex() == Sex.MALE? (int) (11.9 * getIdealWeight() + 700) : (int) (9.2 * getIdealWeight() + 688);
        }
        return getSex() == Sex.MALE? (int) (8.4 * getIdealWeight() + 819) : (int) (9.8 * getIdealWeight() + 624);
    }

    private double calculateLaf(){
        double tot = getPhysicalActivity().getPAValue() + getWork().getWorkValue();
        double avg = tot / 2;
        int age = getAge();
        if(age >= 18 && age <= 59){
            if (avg < 2){
                return getSex() == Sex.MALE? 1.55 : 1.56;
            }
            if (avg < 3){
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
        return (int) (calculateLaf() * mb);
    }

    private int getAge(){
        return LocalDate.now().getYear() - getDob().getYear();
    }
}
