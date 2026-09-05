package com.example.taskmanagementsystem.entity.Strategy;

import com.example.taskmanagementsystem.entity.Task;

import java.util.List;

public interface TaskSortStrategy {

    List<Task> sort(List<Task> tasks);
}