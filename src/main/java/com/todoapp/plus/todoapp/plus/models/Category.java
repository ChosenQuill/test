package com.todoapp.plus.todoapp.plus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
