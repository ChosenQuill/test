package com.todoapp.plus.todoapp.plus.controllers;

import com.todoapp.plus.todoapp.plus.models.Reminder;
import com.todoapp.plus.todoapp.plus.models.TodoModel;
import com.todoapp.plus.todoapp.plus.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ReminderController {
    @Autowired
    private ReminderRepository repository;

    @PostMapping("/reminder")
    @Deprecated(forRemoval = true)
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return repository.save(reminder);
    }

    @GetMapping("/reminder")
    public Iterable<Reminder> listReminders() {
        return repository.findAll();
    }

    @GetMapping("/reminder/{id}")
    public Reminder getReminder(@PathVariable Integer id) {
        Reminder result = repository.getReminderById(id);
        if(result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reminder not found");
        return result;
    }

    @Deprecated //Would require properly adjusting TodoRepository for this to work properly
    @DeleteMapping("/reminder/{id}")
    public int removeReminder(@PathVariable Integer id) {
        return repository.deleteReminderById(id);
    }
}
