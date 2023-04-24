package com.todoapp.plus.todoapp.plus.repository;

import com.todoapp.plus.todoapp.plus.models.Reminder;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface ReminderRepository extends CrudRepository<Reminder, Integer> {
    Reminder getReminderById(int id);

    @Transactional
    int deleteReminderById(int id);
}
