package com.tiagosilva.todo.service;

import com.tiagosilva.todo.domain.Task;
import com.tiagosilva.todo.dto.TaskDto;
import com.tiagosilva.todo.enums.EnumTaskStatus;
import com.tiagosilva.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return  taskRepository.findAll();
    }


    public Task save(TaskDto dto) {
        return taskRepository.save(toTask(dto));
    }

    private Task toTask(TaskDto dto) {
        return Task.builder()
                .title(dto.title())
                .description(dto.description())
                .status(EnumTaskStatus.BACKLOG)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
