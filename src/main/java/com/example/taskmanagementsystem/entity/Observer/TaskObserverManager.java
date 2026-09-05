package com.example.taskmanagementsystem.entity.Observer;

import com.example.taskmanagementsystem.entity.Status;
import com.example.taskmanagementsystem.entity.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskObserverManager {
    private final TaskStatusObserver taskStatusObserver;

    public TaskObserverManager(TaskStatusObserver taskStatusObserver) {
        this.taskStatusObserver = taskStatusObserver;
    }
    public void notifyObservers(Task task,
                                Status oldStatus,
                                Status newStatus) {

        taskStatusObserver.update(task, oldStatus, newStatus);
    }
}