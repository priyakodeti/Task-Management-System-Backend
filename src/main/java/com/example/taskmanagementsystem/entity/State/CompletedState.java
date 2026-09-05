package com.example.taskmanagementsystem.entity.State;

import com.example.taskmanagementsystem.entity.State.TaskState;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;

public class CompletedState implements TaskState {

    @Override
    public void start(Task task, User user) {
        throw new IllegalStateException(
                "Completed task cannot be started again"
        );
    }

    @Override
    public void complete(Task task, User user) {
        throw new IllegalStateException(
                "Task is already completed"
        );
    }
}