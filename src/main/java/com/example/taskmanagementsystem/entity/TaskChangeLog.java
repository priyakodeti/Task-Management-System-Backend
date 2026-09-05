package com.example.taskmanagementsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TaskChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User changedBy;

    private LocalDateTime changedAt;

    @ManyToOne
    private Task changedTask;

    @Enumerated(EnumType.STRING)
    private ChangeType changeType;

    private String oldValue;

    private String newValue;

    public TaskChangeLog() {
    }

    public TaskChangeLog(User changedBy,
                         LocalDateTime changedAt,
                         Task changedTask,
                         ChangeType changeType,
                         String oldValue,
                         String newValue) {

        this.changedBy = changedBy;
        this.changedAt = changedAt;
        this.changedTask = changedTask;
        this.changeType = changeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Long getId() {
        return id;
    }

    public User getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(User changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public Task getChangedTask() {
        return changedTask;
    }

    public void setChangedTask(Task changedTask) {
        this.changedTask = changedTask;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}