package com.example.taskmanagementsystem.entity.Strategy;

import com.example.taskmanagementsystem.entity.Task;

import java.util.Comparator;
import java.util.List;

public class StatusSortStrategy implements TaskSortStrategy {

    @Override
    public List<Task> sort(List<Task> tasks) {

        tasks.sort(
                Comparator.comparing(Task::getStatus)
        );

        return tasks;
    }
}