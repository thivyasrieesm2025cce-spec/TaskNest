package com.example.tasknest.repository;

import com.example.tasknest.entity.Task;
import com.example.tasknest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository
        extends JpaRepository<Task, Long> {
    List<Task> findByDueDateBeforeAndCompletedFalse(LocalDate dueDateBefore);

    List<Task> findByDueDateAndCompletedFalse(LocalDate dueDate);
}