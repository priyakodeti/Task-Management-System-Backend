package com.example.taskmanagementsystem.entity.Strategy;

import com.example.taskmanagementsystem.entity.Task;

import java.util.List;

public class TaskSortContext {

    private TaskSortStrategy strategy;

    public TaskSortContext(TaskSortStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Task> sortTasks(List<Task> tasks) {
        return strategy.sort(tasks);
    }
}