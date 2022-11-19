package org.example.entities;

import javax.persistence.*;

@Entity
public class Task {
    @GeneratedValue
    @Id
    private long id;
    @Column
    private String name;
    @Column
    @Lob
    private String description;
    @Column
    private Double cost;
    @Column
    private Boolean isDone = false;

    public Task() {
        this.cost = 2000.0;
        this.name = "Zadanie";
        this.description = "Zadanie do wykonania";
    }

    @Override
    public String toString() {
        return String.format("Entity Task {id = %d, name = %s, description = %s, cost = %.2f, is done = %b}", id, name, description, cost, isDone);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
