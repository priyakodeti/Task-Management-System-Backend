package com.example.taskmanagementsystem.entity.Strategy;

import com.example.taskmanagementsystem.entity.Task;

import java.util.Comparator;
import java.util.List;

public class DueDateSortStrategy implements TaskSortStrategy {

    @Override
    public List<Task> sort(List<Task> tasks) {

        tasks.sort(
                Comparator.comparing(
                        Task::getDueDate,
                        Comparator.nullsLast(Comparator.naturalOrder())
                )
        );

        return tasks;
    }
}