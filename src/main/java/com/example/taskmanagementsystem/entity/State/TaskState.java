package com.example.taskmanagementsystem.entity.State;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;

public interface TaskState {

    void start(Task task, User user);

    void complete(Task task, User user);
}