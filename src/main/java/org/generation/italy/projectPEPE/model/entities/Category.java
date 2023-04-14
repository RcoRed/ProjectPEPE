package org.generation.italy.projectPEPE.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(generator = "category_generator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "category_generator", sequenceName = "category_sequence", allocationSize = 1)
    @Column(name = "id_category")
    private long id;
    private String name;

    public Category() {
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
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
}
