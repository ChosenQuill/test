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
    public TodoModel addTodoWithBody(@RequestBody TodoModel todo) {
        Category category = todo.getCategory();

        if(category != null && categoryRepository.getCategoryByName(category.getName()) == null) {
            categoryRepository.save(category);
        }
        if(category != null && categoryRepository.getCategoryByName(category.getName()) != null) {
            todo.setCategory(categoryRepository.getCategoryByName(category.getName())); //JPA is unhappy that the reference doesn't match
        }
        return repository.save(todo);
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
                            @RequestBody Reminder reminder) {
        TodoModel model = repository.getTodoById(id);

        reminderRepository.save(reminder);

        model.addReminder(reminder);

        repository.save(model);
    }
}
