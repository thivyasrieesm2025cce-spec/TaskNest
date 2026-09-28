package com.example.tasknest.repository;

import com.example.tasknest.entity.TaskList;
import com.example.tasknest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository
        extends JpaRepository<TaskList, Long> {
}