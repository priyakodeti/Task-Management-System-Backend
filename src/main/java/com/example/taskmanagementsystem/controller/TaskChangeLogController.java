package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.entity.TaskChangeLog;
import com.example.taskmanagementsystem.service.TaskChangeLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskChangeLogController {

    private final TaskChangeLogService taskChangeLogService;

    public TaskChangeLogController(TaskChangeLogService taskChangeLogService) {
        this.taskChangeLogService = taskChangeLogService;
    }

    @GetMapping("/{taskId}/changes")
    public List<TaskChangeLog> getTaskChanges(@PathVariable Long taskId) {

        return taskChangeLogService.getLogsForTask(taskId);
    }
}