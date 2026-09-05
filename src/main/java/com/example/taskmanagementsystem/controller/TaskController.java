package com.example.taskmanagementsystem.controller;
import com.example.taskmanagementsystem.dto.TaskRequest;
import com.example.taskmanagementsystem.dto.TaskSearchFilterResponse;
import com.example.taskmanagementsystem.dto.TaskSortResponse;
import com.example.taskmanagementsystem.entity.Priority;
import com.example.taskmanagementsystem.entity.Status;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}/title")
    public Task updateTitle(@PathVariable Long id,
                            @RequestParam Long userId,
                            @RequestBody String newTitle) {
        return taskService.updateTitle(id, newTitle, userId);
    }

    @PutMapping("/{id}/description")
    public Task updateDescription(@PathVariable Long id,
                                  @RequestParam Long userId,
                                  @RequestBody String newDescription) {
        return taskService.updateDescription(id, newDescription, userId);
    }

    @PutMapping("/{id}/due-date")
    public Task updateDueDate(@PathVariable Long id,
                              @RequestParam Long userId,
                              @RequestBody String newDueDate) {

        LocalDateTime dueDate = LocalDateTime.parse(newDueDate.replace("\"", ""));

        return taskService.updateDueDate(id, dueDate, userId);
    }

    @PutMapping("/{id}/priority")
    public Task updatePriority(@PathVariable Long id,
                               @RequestParam Long userId,
                               @RequestBody Priority newPriority) {

        return taskService.updatePriority(id, newPriority, userId);
    }

    @PutMapping("/{id}/assignee")
    public Task updateAssignee(@PathVariable Long id,
                               @RequestParam Long changedById,
                               @RequestBody Long userId) {

        return taskService.updateAssignee(id, userId, changedById);
    }
    @PostMapping("/{id}/start")
    public Task startTask(@PathVariable Long id,
                          @RequestBody Long userId) {

        return taskService.startTask(id, userId);
    }

    @PostMapping("/{id}/complete")
    public Task completeTask(@PathVariable Long id,
                             @RequestBody Long userId) {

        return taskService.completeTask(id, userId);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


    @PostMapping("/{parentTaskId}/subtasks")
    public Task createSubtask(@PathVariable Long parentTaskId,
                              @RequestBody TaskRequest request) {
        return taskService.createSubtask(parentTaskId, request);
    }
    @PostMapping("/{taskId}/tags/{tagId}")
    public Task addTagToTask(@PathVariable Long taskId,
                             @PathVariable Long tagId) {
        return taskService.addTagToTask(taskId, tagId);
    }
    @DeleteMapping("/{taskId}/tags/{tagId}")
    public Task removeTagFromTask(@PathVariable Long taskId,
                                  @PathVariable Long tagId) {
        return taskService.removeTagFromTask(taskId, tagId);
    }

    @GetMapping("/sort")
    public List<TaskSortResponse> sortTasks(@RequestParam String sortBy) {
        return taskService.sortTasks(sortBy);
    }

    @GetMapping("/search")
    public List<TaskSearchFilterResponse> searchTasks(
            @RequestParam String keyword) {

        return taskService.searchTasks(keyword);
    }

    @GetMapping("/filter/status")
    public List<TaskSearchFilterResponse> filterByStatus(
            @RequestParam Status status) {

        return taskService.filterByStatus(status);
    }

    @GetMapping("/filter/priority")
    public List<TaskSearchFilterResponse> filterByPriority(
            @RequestParam Priority priority) {

        return taskService.filterByPriority(priority);
    }

    @GetMapping("/filter/assignee")
    public List<TaskSearchFilterResponse> filterByAssignee(
            @RequestParam Long userId) {

        return taskService.filterByAssignee(userId);
    }

}

