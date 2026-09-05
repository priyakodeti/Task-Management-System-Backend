package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.entity.TaskChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskChangeLogRepository extends JpaRepository<TaskChangeLog, Long> {
    List<TaskChangeLog> findByChangedTaskId(Long taskId);
}