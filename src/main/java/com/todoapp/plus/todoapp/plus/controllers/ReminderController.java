package com.todoapp.plus.todoapp.plus.controllers;

import com.todoapp.plus.todoapp.plus.models.Reminder;
import com.todoapp.plus.todoapp.plus.models.TodoModel;
import com.todoapp.plus.todoapp.plus.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ReminderController {
    @Autowired
    private ReminderRepository repository;
    private final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @PostMapping("/reminder")
    public Reminder createReminder(@RequestParam String name, @RequestParam String dateString) {
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }

        Reminder reminder = new Reminder();
        reminder.setReminderDate(date);
        reminder.setName(name);

        repository.save(reminder);

        return reminder;
    }

    @GetMapping("/reminder")
    public Iterable<Reminder> listReminders() {
        return repository.findAll();
    }

    @GetMapping("/reminder/{id}")
    public Reminder getReminder(@PathVariable Integer id) {
        return repository.getReminderById(id);
    }

    @DeleteMapping("/reminder/{id}")
    public int removeReminder(@PathVariable Integer id) {
        return repository.deleteReminderById(id);
    }
}
