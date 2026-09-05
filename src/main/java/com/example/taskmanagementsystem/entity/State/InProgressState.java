package com.example.taskmanagementsystem.entity.State;

import com.example.taskmanagementsystem.entity.State.TaskState;
import com.example.taskmanagementsystem.entity.Status;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;
public class InProgressState implements TaskState {

    @Override
    public void start(Task task, User user) {
        throw new IllegalStateException(
                "Task is already in progress"
        );
    }

    @Override
    public void complete(Task task, User user) {
        task.setStatus(Status.COMPLETED);
        task.setTaskState(new CompletedState());
    }
}