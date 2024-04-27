package com.tiagosilva.todo.repository;

import com.tiagosilva.todo.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {
    Optional<Task> findById(String id);
}
