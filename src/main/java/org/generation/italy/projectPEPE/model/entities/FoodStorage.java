package org.generation.italy.projectPEPE.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "food_storage")
public class FoodStorage {
    @Id
    @Column(name = "id_food_storage")
    @GeneratedValue(generator = "food_storage_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "food_storage_generator", sequenceName = "food_storage_sequence", allocationSize = 1)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_food")
    private Food food;
    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person person;
    private int quantity;

    public FoodStorage() {
    }

    public FoodStorage(long id, Food food, Person person, int quantity) {
        this.id = id;
        this.food = food;
        this.person = person;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
