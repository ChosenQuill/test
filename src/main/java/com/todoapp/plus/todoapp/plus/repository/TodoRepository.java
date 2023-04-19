package com.todoapp.plus.todoapp.plus.repository;

import com.todoapp.plus.todoapp.plus.models.TodoModel;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<TodoModel, Integer> {
    TodoModel getTodoById(Integer id);

}
