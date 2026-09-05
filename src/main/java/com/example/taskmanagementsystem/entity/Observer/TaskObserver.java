package com.example.taskmanagementsystem.entity.Observer;

import com.example.taskmanagementsystem.entity.Status;
import com.example.taskmanagementsystem.entity.Task;

public interface TaskObserver {

    void update(Task task, Status oldStatus, Status newStatus);
}