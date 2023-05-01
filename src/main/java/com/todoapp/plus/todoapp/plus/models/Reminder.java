package com.todoapp.plus.todoapp.plus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Reminder {
    private Date reminderDate;
    private String name;
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Reminder() {}

    public Reminder(Date reminderDate, String name) {
        this.reminderDate = reminderDate;
        this.name = name;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
