package com.todoapp.plus.todoapp.plus.repository;

import com.todoapp.plus.todoapp.plus.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category getCategoryById(int id);
    Category getCategoryByName(String name);
    int deleteCategoryById(int id);
}
