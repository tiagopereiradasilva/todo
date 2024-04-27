package com.tiagosilva.todo.repository;

import com.tiagosilva.todo.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
