package com.example.taskmanagementsystem.entity.Strategy;

import com.example.taskmanagementsystem.entity.Task;

import java.util.Comparator;
import java.util.List;

public class PrioritySortStrategy implements TaskSortStrategy {

    @Override
    public List<Task> sort(List<Task> tasks) {

        tasks.sort(
                Comparator.comparing(
                        Task::getPriority
                ).reversed()
        );

        return tasks;
    }
}