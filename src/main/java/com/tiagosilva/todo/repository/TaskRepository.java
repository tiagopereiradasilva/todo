package com.tiagosilva.todo.repository;

import com.tiagosilva.todo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {

}
