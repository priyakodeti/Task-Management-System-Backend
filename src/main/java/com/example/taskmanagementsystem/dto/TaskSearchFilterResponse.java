package com.example.taskmanagementsystem.dto;

public class TaskSearchFilterResponse {

    private Long id;
    private String title;
    private String value;

    public TaskSearchFilterResponse(Long id, String title, String value) {
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