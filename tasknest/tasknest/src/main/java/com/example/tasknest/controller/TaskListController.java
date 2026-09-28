package com.example.tasknest.controller;

import com.example.tasknest.entity.TaskList;
import com.example.tasknest.service.TaskListService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasklists")
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping
    public TaskList createTaskList(@Valid @RequestBody TaskList taskList) {
        return taskListService.createTaskList(taskList);
    }

    @GetMapping
    public List<TaskList> getAllTaskLists() {
        return taskListService.getAllTaskLists();
    }

    @GetMapping("/{id}")
    public TaskList getTaskListById(@PathVariable Long id) {
        return taskListService.getTaskListById(id);
    }

    @PutMapping("/{id}")
    public TaskList updateTaskList(
            @PathVariable Long id,
            @Valid @RequestBody TaskList taskList) {

        return taskListService.updateTaskList(id, taskList);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskList(@PathVariable Long id) {
        taskListService.deleteTaskList(id);
    }
}