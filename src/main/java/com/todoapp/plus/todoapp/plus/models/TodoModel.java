package com.todoapp.plus.todoapp.plus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TodoModel {
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Set<Reminder> getReminders() { return reminders; }

    public void addReminder(Reminder reminder) {
        if(reminders == null) reminders = new HashSet<>();
        reminders.add(reminder);
    }

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private Date dueDate;
    private String description;
    private int priority;
    @OneToOne
    private Category category;
    @OneToMany
    private Set<Reminder> reminders;

    public TodoModel() {}

    public TodoModel(String title, Date dueDate, String description, int priority, Category category) {
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
        this.priority = priority;
        this.category = category;
    }

    public TodoModel(String title, Date dueDate, String description, int priority, Category category,
                     Set<Reminder> reminders) {
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
        this.priority = priority;
        this.category = category;
        this.reminders = reminders;
    }
}
