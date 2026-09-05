package com.example.taskmanagementsystem.entity;
import com.example.taskmanagementsystem.entity.State.CompletedState;
import com.example.taskmanagementsystem.entity.State.InProgressState;
import com.example.taskmanagementsystem.entity.State.TaskState;
import com.example.taskmanagementsystem.entity.State.TodoState;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status = Status.TODO;

    // State Pattern object - not stored in the database
    private transient TaskState taskState;

    @ManyToMany
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User assignedTo;

    @ManyToOne
    @JsonBackReference
    private Task parentTask;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> subtasks = new ArrayList<>();

    public Task() {
    }

    public Task(String title, String description,
                LocalDateTime dueDate, Priority priority) {

        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = Status.TODO;
        this.taskState = new TodoState();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public void start(User user) {
        taskState.start(this, user);
    }

    public void complete(User user) {
        taskState.complete(this, user);
    }

    public void initializeState() {

        if (status == Status.TODO) {
            taskState = new TodoState();

        } else if (status == Status.IN_PROGRESS) {
            taskState = new InProgressState();

        } else if (status == Status.COMPLETED) {
            taskState = new CompletedState();
        }
    }
    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public List<Task> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Task> subtasks) {
        this.subtasks = subtasks;
    }
    public void addSubtask(Task subtask) {
        subtasks.add(subtask);
        subtask.setParentTask(this);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

}
