package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateTaskRequest;
import org.example.dto.UpdateTaskRequest;
import org.example.model.Task;
import org.example.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ActivityLogService activityLogService;
    private final WebSocketService webSocketService;

    // CREATE
    // CREATE
    public Task createTask(Long userId, Long groupId, CreateTaskRequest req) {

        LocalDateTime deadline = (req.getDeadline() != null)
                ? LocalDate.parse(req.getDeadline()).atStartOfDay()
                : null;

        Task task = new Task(
                groupId,
                userId,
                req.getTitle(),
                req.getDescription(),
                deadline
        );

        Task saved = taskRepository.save(task);

        activityLogService.log(userId, "TASK_CREATED",
                "Task ID: " + saved.getId());

        webSocketService.sendToGroup(groupId,
                "task.created", saved);

        return saved;
    }


    // LIST
    public List<Task> listTasks(Long groupId) {
        return taskRepository.findByGroupId(groupId);
    }

    // UPDATE
    // UPDATE
    public Task updateTask(Long taskId, UpdateTaskRequest req) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (req.getTitle() != null) task.setTitle(req.getTitle());
        if (req.getDescription() != null) task.setDescription(req.getDescription());

        if (req.getDeadline() != null) {
            task.setDeadline(
                    LocalDate.parse(req.getDeadline()).atStartOfDay()
            );
        }

        if (req.getStatus() != null) task.setStatus(req.getStatus());
        if (req.getAssignedTo() != null) task.setAssignedTo(req.getAssignedTo());

        task.setUpdatedAt(LocalDateTime.now());

        Task saved = taskRepository.save(task);

        activityLogService.log(task.getCreatedBy(),
                "TASK_UPDATED",
                "Task ID: " + taskId);

        webSocketService.sendToGroup(task.getGroupId(),
                "task.updated", saved);

        return saved;
    }


    // STATUS CHANGE
    public Task updateTaskStatus(Long taskId, String status) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);
        task.setUpdatedAt(LocalDateTime.now());

        Task saved = taskRepository.save(task);

        activityLogService.log(task.getCreatedBy(),
                "TASK_STATUS_CHANGED",
                "Task ID: " + taskId + " -> " + status);

        webSocketService.sendToGroup(task.getGroupId(),
                "task.statusChanged", saved);

        return saved;
    }

    // ASSIGN
    public Task assignTask(Long taskId, Long userId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setAssignedTo(userId);
        task.setUpdatedAt(LocalDateTime.now());

        Task saved = taskRepository.save(task);

        activityLogService.log(userId,
                "TASK_ASSIGNED",
                "Task ID: " + taskId + " assigned to " + userId);

        webSocketService.sendToGroup(task.getGroupId(),
                "task.assigned", saved);

        return saved;
    }
}
