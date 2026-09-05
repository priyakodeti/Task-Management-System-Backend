package com.example.taskmanagementsystem.entity.State;

import com.example.taskmanagementsystem.entity.State.TaskState;
import com.example.taskmanagementsystem.entity.Status;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;

public class TodoState implements TaskState {

    @Override
    public void start(Task task, User user) {
        task.setStatus(Status.IN_PROGRESS);
        task.setTaskState(new InProgressState());
    }

    @Override
    public void complete(Task task, User user) {
        throw new IllegalStateException(
                "Task must be started before it can be completed"
        );
    }
}