package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dto.CommentResponse;
import com.example.taskmanagementsystem.entity.Comment;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repository.CommentRepository;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          TaskRepository taskRepository,
                          UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Comment addComment(Long taskId, Long userId, String text) {

        Task task = taskRepository.findById(taskId)
                .orElse(null);

        User user = userRepository.findById(userId)
                .orElse(null);

        if (task == null || user == null) {
            return null;
        }

        Comment comment = new Comment(
                text,
                user,
                LocalDateTime.now(),
                task
        );

        return commentRepository.save(comment);
    }

    public List<CommentResponse> getCommentsForTask(Long taskId) {

        List<Comment> comments = commentRepository.findByTaskId(taskId);

        return comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getText(),
                        comment.getCommentedBy().getId(),
                        comment.getCommentedBy().getName(),
                        comment.getCommentedAt()
                ))
                .toList();
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}