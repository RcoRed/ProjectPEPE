package org.generation.italy.projectPEPE.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "avoiding_food")
public class AvoidingFood {
    @Id
    @GeneratedValue(generator = "avoiding_food_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "avoiding_food_generator", sequenceName = "avoiding_food_sequence", allocationSize = 1)
    @Column(name = "id_avoiding_food")
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_person")
    private  Person person;
    @ManyToOne
    @JoinColumn(name = "id_food")
    private Food food;

    public AvoidingFood() {
    }

    public AvoidingFood(long id, Person person, Food food) {
        this.id = id;
        this.person = person;
        this.food = food;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
