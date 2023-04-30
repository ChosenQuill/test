package com.todoapp.plus.todoapp.plus.controllers;

import com.todoapp.plus.todoapp.plus.models.Category;
import com.todoapp.plus.todoapp.plus.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return repository.getCategoryById(id);
    }

    @DeleteMapping("/category/{id}")
    public int deleteCategory(@PathVariable int id) {
        return repository.deleteCategoryById(id);
    }
}
