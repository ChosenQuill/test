package com.todoapp.plus.todoapp.plus.models;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public void addReminder(Reminder reminder) {
        if(reminders == null) reminders = new HashSet<>();
        reminders.add(reminder);
    }

    @Id
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
}
