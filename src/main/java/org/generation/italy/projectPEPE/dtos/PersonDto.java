package org.generation.italy.projectPEPE.dtos;

import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.generation.italy.projectPEPE.model.entities.Food;
import org.generation.italy.projectPEPE.model.entities.FoodStorage;
import org.generation.italy.projectPEPE.model.entities.Person;
import org.generation.italy.projectPEPE.model.entities.PersonAuth;
import org.generation.italy.projectPEPE.model.entities.enums.Diet;
import org.generation.italy.projectPEPE.model.entities.enums.PhysicalActivity;
import org.generation.italy.projectPEPE.model.entities.enums.Sex;
import org.generation.italy.projectPEPE.model.entities.enums.Work;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.StreamSupport;

@Getter
@Setter
public class PersonDto {
    private long id;
    private String firstname;
    private String lastname;
    private LocalDate dob;
    private int weight;
    private int height;
    private Sex sex;
    private Work work;
    private Diet diet;
    private PhysicalActivity physicalActivity;
    private double idealWeight;
    private int calorieReq;
    private String email;

    public PersonDto(long id, String firstname, String lastname, LocalDate dob,
                     int weight, int height, Sex sex, Work work, Diet diet,
                     PhysicalActivity physicalActivity, double idealWeight, int calorieReq, String email) {
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
        this.idealWeight = idealWeight;
        this.calorieReq = calorieReq;
        this.email = email;
    }

    public static PersonDto fromEntity(Person person){
        return new PersonDto(person.getId(), person.getFirstname(), person.getLastname(), person.getDob(),
                person.getWeight(), person.getHeight(), person.getSex(), person.getWork(),
                person.getDiet(), person.getPhysicalActivity(), person.getIdealWeight(), person.getCalorieReq(), person.getEmail());
    }

    public static Iterable<PersonDto> fromEntityIterator(Iterable<Person> people){
        return StreamSupport.stream(people.spliterator(), false)
                .map(PersonDto::fromEntity).toList();

    }
}
