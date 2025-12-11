package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private String status = "OPEN";

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "assigned_to")
    private Long assignedTo;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Task() {}

    public Task(Long groupId, Long createdBy, String title, String description, LocalDateTime deadline) {
        this.groupId = groupId;
        this.createdBy = createdBy;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
    }

    // ==== GETTERS ====
    public Long getId() { return id; }
    public Long getGroupId() { return groupId; }
    public Long getCreatedBy() { return createdBy; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDateTime getDeadline() { return deadline; }
    public Long getAssignedTo() { return assignedTo; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    // ==== SETTERS ====
    public void setId(Long id) { this.id = id; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
    public void setAssignedTo(Long assignedTo) { this.assignedTo = assignedTo; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
