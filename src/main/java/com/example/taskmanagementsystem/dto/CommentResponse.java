package com.example.taskmanagementsystem.dto;

import java.time.LocalDateTime;

public class CommentResponse {

    private Long id;
    private String text;
    private Long commentedById;
    private String commentedByName;
    private LocalDateTime commentedAt;

    public CommentResponse() {
    }

    public CommentResponse(Long id,
                           String text,
                           Long commentedById,
                           String commentedByName,
                           LocalDateTime commentedAt) {
        this.id = id;
        this.text = text;
        this.commentedById = commentedById;
        this.commentedByName = commentedByName;
        this.commentedAt = commentedAt;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getCommentedById() {
        return commentedById;
    }

    public String getCommentedByName() {
        return commentedByName;
    }

    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }
}