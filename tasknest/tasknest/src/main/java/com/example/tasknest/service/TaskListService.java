package com.example.tasknest.service;

import com.example.tasknest.entity.TaskList;
import com.example.tasknest.repository.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public TaskList createTaskList(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    public TaskList getTaskListById(Long id) {
        return taskListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task list not found"));
    }

    public TaskList updateTaskList(Long id, TaskList taskList) {

        TaskList existingTaskList = getTaskListById(id);

        existingTaskList.setName(taskList.getName());
        existingTaskList.setUser(taskList.getUser());

        return taskListRepository.save(existingTaskList);
    }

    public void deleteTaskList(Long id) {

        TaskList existingTaskList = getTaskListById(id);

        taskListRepository.delete(existingTaskList);
    }
}