package com.todoapp.plus.todoapp.plus.controllers;

import com.todoapp.plus.todoapp.plus.models.Category;
import com.todoapp.plus.todoapp.plus.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository repository;

    @PostMapping("/category")
    public Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);

        return repository.save(category);
    }

    @GetMapping("/category")
    public Iterable<Category> getCategories() {
        return repository.findAll();
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable int id) {
        Category result = repository.getCategoryById(id);
        if(result == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        return result;
    }

    @DeleteMapping("/category/{id}")
    public int deleteCategory(@PathVariable int id) {
        return repository.deleteCategoryById(id);
    }
}
