package com.todoapp.plus.todoapp.plus.controllers;

import com.todoapp.plus.todoapp.plus.models.Category;
import com.todoapp.plus.todoapp.plus.models.Reminder;
import com.todoapp.plus.todoapp.plus.models.TodoModel;
import com.todoapp.plus.todoapp.plus.repository.CategoryRepository;
import com.todoapp.plus.todoapp.plus.repository.ReminderRepository;
import com.todoapp.plus.todoapp.plus.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
class TodoController {
    @Autowired
    private TodoRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ReminderRepository reminderRepository;

    private final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @PostMapping("/todo")
    public TodoModel addTodo(@RequestParam String title,
                        @RequestParam String dueDateString,
                        @RequestParam String category,
                        @RequestParam String description, @RequestParam int priority) {
        Date dueDate;
        try {
            dueDate = dateFormat.parse(dueDateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
        Category taskCategory = null;

        if(category != null && !category.isEmpty()) {
            taskCategory = categoryRepository.getCategoryByName(category);
            if(taskCategory == null) {
                taskCategory = new Category(category);
                categoryRepository.save(taskCategory);
            }
        }

        TodoModel model = new TodoModel(title, dueDate, description, priority, taskCategory);

        repository.save(model);

        return model;
    }

    @GetMapping("/todo")
    public Iterable<TodoModel> listTodo() {
        return repository.findAll();
    }

    @GetMapping("/todo/{id}")
    public TodoModel getTodo(@PathVariable int id) {
        TodoModel result = repository.getTodoById(id);
        if(result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ToDo not found");
        return result;
    }

    @PostMapping("/todo/{id}/reminder")
    public void addReminder(@PathVariable int id,
                            @RequestParam String name,
                            @RequestParam(required = false) String dateString) {
        TodoModel model = repository.getTodoById(id);

        Date date;
        if(dateString == null) {
            date = model.getDueDate();
        } else {
            try {
                date = dateFormat.parse(dateString);
            } catch (ParseException e) {
                throw new IllegalArgumentException();
            }
        }

        Reminder reminder = new Reminder(date, name);

        reminderRepository.save(reminder);

        model.addReminder(reminder);
    }
}
