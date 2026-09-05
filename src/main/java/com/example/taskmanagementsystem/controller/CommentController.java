package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.dto.CommentResponse;
import com.example.taskmanagementsystem.entity.Comment;
import com.example.taskmanagementsystem.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{taskId}/comments")
    public Comment addComment(@PathVariable Long taskId,
                              @RequestParam Long userId,
                              @RequestBody String text) {
        return commentService.addComment(taskId, userId, text);
    }

    @GetMapping("/{taskId}/comments")
    public List<CommentResponse> getCommentsForTask(@PathVariable Long taskId) {
        return commentService.getCommentsForTask(taskId);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}