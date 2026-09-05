package com.example.taskmanagementsystem.repository;

import com.example.taskmanagementsystem.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}