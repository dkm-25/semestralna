package org.example.controller;

import org.example.dto.CreateTaskRequest;
import org.example.dto.UpdateTaskRequest;
import org.example.dto.UpdateTaskStatusRequest;
import org.example.dto.AssignTaskRequest;
import org.example.model.Task;
import org.example.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;


import java.util.List;

@CrossOrigin(origins = "http://localhost:7777")
@RestController
@RequestMapping("/groups/{groupId}/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/{taskId}/status")
    public Task changeStatusPost(
            @PathVariable Long groupId,
            @PathVariable Long taskId,
            @RequestBody UpdateTaskStatusRequest req
    ) {
        return taskService.updateTaskStatus(taskId, req.getStatus());
    }




    // CREATE TASK
    @PostMapping
    public Task createTask(
            @PathVariable Long groupId,
            @RequestParam Long userId,
            @RequestBody CreateTaskRequest req
    ) {
        return taskService.createTask(userId, groupId, req);
    }

    // LIST TASKS
    @GetMapping
    public List<Task> listTasks(@PathVariable Long groupId) {
        return taskService.listTasks(groupId);
    }

    // UPDATE TASK
    @PutMapping("/{taskId}")
    public Task updateTask(
            @PathVariable Long groupId,
            @PathVariable Long taskId,
            @RequestBody UpdateTaskRequest req
    ) {
        return taskService.updateTask(taskId, req);
    }

    // CHANGE STATUS
    @PatchMapping("/{taskId}/status")
    public Task changeStatus(
            @PathVariable Long groupId,
            @PathVariable Long taskId,
            @RequestBody UpdateTaskStatusRequest req
    ) {
        return taskService.updateTaskStatus(taskId, req.getStatus());
    }

    // ASSIGN TASK
    @PatchMapping("/{taskId}/assign")
    public Task assignTask(
            @PathVariable Long groupId,
            @PathVariable Long taskId,
            @RequestBody AssignTaskRequest req
    ) {
        return taskService.assignTask(taskId, req.getUserId());
    }
}
