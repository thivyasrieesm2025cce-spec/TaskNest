package com.example.tasknest.service;

import com.example.tasknest.entity.Task;
import com.example.tasknest.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        validatePriority(task.getPriority());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTask(Long id, Task task) {

        Task existingTask = getTaskById(id);

        validatePriority(task.getPriority());

        existingTask.setTitle(task.getTitle());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setCompleted(task.isCompleted());
        existingTask.setTaskList(task.getTaskList());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        Task existingTask = getTaskById(id);
        taskRepository.delete(existingTask);
    }

    public List<Task> getDueTodayTasks() {
        LocalDate today = LocalDate.now();

        return taskRepository.findByDueDateAndCompletedFalse(today);
    }

    public List<Task> getOverdueTasks() {
        LocalDate today = LocalDate.now();

        return taskRepository.findByDueDateBeforeAndCompletedFalse(today);
    }

    private void validatePriority(String priority) {

        if (priority == null
                || (!priority.equals("LOW")
                && !priority.equals("MEDIUM")
                && !priority.equals("HIGH"))) {

            throw new IllegalArgumentException(
                    "Priority must be LOW, MEDIUM or HIGH"
            );
        }
    }
}