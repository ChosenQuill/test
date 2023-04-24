package com.todoapp.plus.todoapp.plus.controllers;

import com.todoapp.plus.todoapp.plus.models.Category;
import com.todoapp.plus.todoapp.plus.models.TodoModel;
import com.todoapp.plus.todoapp.plus.repository.CategoryRepository;
import com.todoapp.plus.todoapp.plus.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        TodoModel model = new TodoModel();
        model.setTitle(title);
        model.setDueDate(dueDate);
        model.setPriority(priority);
        model.setDescription(description);

        if(category != null && !category.isEmpty()) {
            Category taskCategory = categoryRepository.getCategoryByName(category);
            if(taskCategory == null) {
                taskCategory = new Category();
                taskCategory.setName(category);
                categoryRepository.save(taskCategory);
            }

            model.setCategory(taskCategory);
        }

        repository.save(model);

        return model;
    }

    @GetMapping("/todo")
    public Iterable<TodoModel> listTodo() {
        return repository.findAll();
    }

    @GetMapping("/todo/{id}")
    public TodoModel getTodo(@PathVariable Integer id) {
        return repository.getTodoById(id);
    }
}
