package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.ChangeType;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.TaskChangeLog;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repository.TaskChangeLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskChangeLogService {

    private final TaskChangeLogRepository taskChangeLogRepository;

    public TaskChangeLogService(TaskChangeLogRepository taskChangeLogRepository) {
        this.taskChangeLogRepository = taskChangeLogRepository;
    }

    public TaskChangeLog logChange(Task task,
                                   User user,
                                   ChangeType changeType,
                                   String oldValue,
                                   String newValue) {

        TaskChangeLog changeLog = new TaskChangeLog(
                user,
                LocalDateTime.now(),
                task,
                changeType,
                oldValue,
                newValue
        );
        return taskChangeLogRepository.save(changeLog);
    }
    public List<TaskChangeLog> getLogsForTask(Long taskId) {

        return taskChangeLogRepository.findByChangedTaskId(taskId);
    }
}