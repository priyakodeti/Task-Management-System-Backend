package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dto.TaskRequest;
import com.example.taskmanagementsystem.dto.TaskSearchFilterResponse;
import com.example.taskmanagementsystem.dto.TaskSortResponse;
import com.example.taskmanagementsystem.entity.*;
import com.example.taskmanagementsystem.entity.Observer.TaskObserverManager;
import com.example.taskmanagementsystem.entity.Strategy.*;
import com.example.taskmanagementsystem.repository.TagRepository;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskChangeLogService taskChangeLogService;
    private final TaskObserverManager taskObserverManager;
    private final TagRepository tagRepository;
    public TaskService(TaskRepository taskRepository,
                       UserRepository userRepository,
                       TagRepository tagRepository,TaskChangeLogService taskChangeLogService,
                        TaskObserverManager taskObserverManager) {

        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskChangeLogService = taskChangeLogService;
        this.taskObserverManager = taskObserverManager;
        this.tagRepository = tagRepository;
    }

    public Task createTask(TaskRequest request) {

        User createdBy = userRepository.findById(request.getCreatedById())
                .orElse(null);

        User assignedTo = null;

        if (request.getAssignedToId() != null) {
            assignedTo = userRepository.findById(request.getAssignedToId())
                    .orElse(null);
        }

        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                request.getDueDate(),
                request.getPriority()
        );
        task.setCreatedBy(createdBy);
        task.setAssignedTo(assignedTo);

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElse(null);
    }
    public Task updateTitle(Long id, String newTitle, Long userId) {

        Task task = taskRepository.findById(id)
                .orElse(null);

        if (task == null) {
            return null;
        }

        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return null;
        }

        String oldTitle = task.getTitle();

        task.setTitle(newTitle);

        Task savedTask = taskRepository.save(task);

        taskChangeLogService.logChange(
                savedTask,
                user,
                ChangeType.TITLE_CHANGED,
                oldTitle,
                newTitle
        );

        return savedTask;
    }

    public Task updateDescription(Long id, String newDescription, Long userId) {

        Task task = taskRepository.findById(id)
                .orElse(null);

        if (task == null) {
            return null;
        }

        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return null;
        }

        String oldDescription = task.getDescription();

        task.setDescription(newDescription);

        Task savedTask = taskRepository.save(task);

        taskChangeLogService.logChange(
                savedTask,
                user,
                ChangeType.DESCRIPTION_CHANGED,
                oldDescription,
                newDescription
        );

        return savedTask;
    }
    public Task updateDueDate(Long id,
                              LocalDateTime newDueDate,
                              Long userId) {

        Task task = taskRepository.findById(id)
                .orElse(null);

        if (task == null) {
            return null;
        }

        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return null;
        }

        String oldDueDate = String.valueOf(task.getDueDate());
        String newDueDateValue = String.valueOf(newDueDate);

        task.setDueDate(newDueDate);

        Task savedTask = taskRepository.save(task);

        taskChangeLogService.logChange(
                savedTask,
                user,
                ChangeType.DUE_DATE_CHANGED,
                oldDueDate,
                newDueDateValue
        );

        return savedTask;
    }

    public Task updatePriority(Long id,
                               Priority newPriority,
                               Long userId) {

        Task task = taskRepository.findById(id)
                .orElse(null);

        if (task == null) {
            return null;
        }

        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return null;
        }

        String oldPriority = String.valueOf(task.getPriority());
        String newPriorityValue = String.valueOf(newPriority);

        task.setPriority(newPriority);

        Task savedTask = taskRepository.save(task);

        taskChangeLogService.logChange(
                savedTask,
                user,
                ChangeType.PRIORITY_CHANGED,
                oldPriority,
                newPriorityValue
        );

        return savedTask;
    }

    public Task updateAssignee(Long id, Long userId, Long changedById) {

        Task task = taskRepository.findById(id)
                .orElse(null);

        if (task == null) {
            return null;
        }

        User changedBy = userRepository.findById(changedById)
                .orElse(null);

        if (changedBy == null) {
            return null;
        }

        User newAssignee = userRepository.findById(userId)
                .orElse(null);

        if (newAssignee == null) {
            return null;
        }

        String oldAssignee = task.getAssignedTo() == null
                ? null
                : String.valueOf(task.getAssignedTo().getId());

        String newAssigneeValue = String.valueOf(newAssignee.getId());

        task.setAssignedTo(newAssignee);

        Task savedTask = taskRepository.save(task);

        taskChangeLogService.logChange(
                savedTask,
                changedBy,
                ChangeType.ASSIGNEE_CHANGED,
                oldAssignee,
                newAssigneeValue
        );

        return savedTask;
    }
    public Task startTask(Long id, Long userId) {

        Task task = taskRepository.findById(id)
                .orElse(null);

        if (task == null) {
            return null;
        }

        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return null;
        }

        Status oldStatus = task.getStatus();

        task.initializeState();

        task.start(user);

        Status newStatus = task.getStatus();

        Task savedTask = taskRepository.save(task);

        taskChangeLogService.logChange(
                savedTask,
                user,
                ChangeType.STATUS_CHANGED,
                String.valueOf(oldStatus),
                String.valueOf(newStatus)
        );

        taskObserverManager.notifyObservers(task, oldStatus, newStatus);
        return savedTask;
    }

    public Task completeTask(Long id, Long userId) {

        Task task = taskRepository.findById(id)
                .orElse(null);

        if (task == null) {
            return null;
        }

        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return null;
        }

        Status oldStatus = task.getStatus();

        task.initializeState();

        task.complete(user);

        Status newStatus = task.getStatus();

        Task savedTask = taskRepository.save(task);

        taskChangeLogService.logChange(
                savedTask,
                user,
                ChangeType.STATUS_CHANGED,
                String.valueOf(oldStatus),
                String.valueOf(newStatus)
        );

        taskObserverManager.notifyObservers(task, oldStatus, newStatus);

        return savedTask;
    }
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task createSubtask(Long parentTaskId, TaskRequest request) {

        Task parentTask = taskRepository.findById(parentTaskId)
                .orElse(null);

        if (parentTask == null) {
            return null;
        }

        User createdBy = userRepository.findById(request.getCreatedById())
                .orElse(null);

        User assignedTo = null;

        if (request.getAssignedToId() != null) {
            assignedTo = userRepository.findById(request.getAssignedToId())
                    .orElse(null);
        }

        Task subtask = new Task(
                request.getTitle(),
                request.getDescription(),
                request.getDueDate(),
                request.getPriority()
        );

        subtask.setCreatedBy(createdBy);
        subtask.setAssignedTo(assignedTo);

        parentTask.addSubtask(subtask);

        taskRepository.save(parentTask);

        return taskRepository.save(subtask);
    }
    public Task addTagToTask(Long taskId, Long tagId) {

        Task task = taskRepository.findById(taskId)
                .orElse(null);

        if (task == null) {
            return null;
        }

        Tag tag = tagRepository.findById(tagId)
                .orElse(null);

        if (tag == null) {
            return null;
        }

        task.addTag(tag);

        return taskRepository.save(task);
    }
    public Task removeTagFromTask(Long taskId, Long tagId) {

        Task task = taskRepository.findById(taskId)
                .orElse(null);

        if (task == null) {
            return null;
        }

        Tag tag = tagRepository.findById(tagId)
                .orElse(null);

        if (tag == null) {
            return null;
        }

        task.getTags().remove(tag);

        return taskRepository.save(task);
    }
    public List<TaskSortResponse> sortTasks(String sortBy) {

        List<Task> tasks = taskRepository.findAll();

        TaskSortStrategy strategy;

        if (sortBy.equalsIgnoreCase("dueDate")) {
            strategy = new DueDateSortStrategy();
        } else if (sortBy.equalsIgnoreCase("priority")) {
            strategy = new PrioritySortStrategy();
        } else if (sortBy.equalsIgnoreCase("status")) {
            strategy = new StatusSortStrategy();
        } else {
            throw new IllegalArgumentException("Invalid sort option");
        }

        TaskSortContext context = new TaskSortContext(strategy);

        List<Task> sortedTasks = context.sortTasks(tasks);

        return sortedTasks.stream()
                .map(task -> {

                    String value;

                    if (sortBy.equalsIgnoreCase("dueDate")) {
                        value = String.valueOf(task.getDueDate());
                    } else if (sortBy.equalsIgnoreCase("priority")) {
                        value = String.valueOf(task.getPriority());
                    } else {
                        value = String.valueOf(task.getStatus());
                    }

                    return new TaskSortResponse(
                            task.getId(),
                            task.getTitle(),
                            value
                    );
                })
                .toList();
    }
    public List<TaskSearchFilterResponse> searchTasks(String keyword) {

        List<Task> tasks =
                taskRepository.findByTitleContainingIgnoreCase(keyword);

        return tasks.stream()
                .map(task -> new TaskSearchFilterResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getTitle()
                ))
                .toList();
    }

    public List<TaskSearchFilterResponse> filterByStatus(Status status) {

        List<Task> tasks = taskRepository.findByStatus(status);

        return tasks.stream()
                .map(task -> new TaskSearchFilterResponse(
                        task.getId(),
                        task.getTitle(),
                        String.valueOf(task.getStatus())
                ))
                .toList();
    }

    public List<TaskSearchFilterResponse> filterByPriority(Priority priority) {

        List<Task> tasks = taskRepository.findByPriority(priority);

        return tasks.stream()
                .map(task -> new TaskSearchFilterResponse(
                        task.getId(),
                        task.getTitle(),
                        String.valueOf(task.getPriority())
                ))
                .toList();
    }

    public List<TaskSearchFilterResponse> filterByAssignee(Long userId) {

        List<Task> tasks = taskRepository.findByAssignedToId(userId);

        return tasks.stream()
                .map(task -> new TaskSearchFilterResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getAssignedTo() != null
                                ? task.getAssignedTo().getName()
                                : "Unassigned"
                ))
                .toList();
    }

}