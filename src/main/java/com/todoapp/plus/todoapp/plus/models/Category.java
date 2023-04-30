package com.todoapp.plus.todoapp.plus.models;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}
