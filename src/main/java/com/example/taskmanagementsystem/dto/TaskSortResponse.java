package com.example.taskmanagementsystem.dto;

public class TaskSortResponse {

    private Long id;
    private String title;
    private String value;

    public TaskSortResponse(Long id, String title, String value) {
        this.id = id;
        this.title = title;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }
}