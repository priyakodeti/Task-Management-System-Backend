package com.example.taskmanagementsystem.entity.Observer;

import com.example.taskmanagementsystem.entity.Status;
import com.example.taskmanagementsystem.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskStatusObserver implements TaskObserver {

    @Override
    public void update(Task task, Status oldStatus, Status newStatus) {

        System.out.println(
                "Task " + task.getId()
                        + " status changed from "
                        + oldStatus
                        + " to "
                        + newStatus
        );
    }
}