package com.tiagosilva.todo.controller;

import com.tiagosilva.todo.domain.Task;
import com.tiagosilva.todo.dto.TaskDto;
import com.tiagosilva.todo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> list(){
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskDto dto){
        return ResponseEntity.ok(taskService.save(dto));
    }
}