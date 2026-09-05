package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.entity.Priority;
import com.example.taskmanagementsystem.entity.Status;
import com.example.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContainingIgnoreCase(String keyword);

    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);

    List<Task> findByAssignedToId(Long userId);
}