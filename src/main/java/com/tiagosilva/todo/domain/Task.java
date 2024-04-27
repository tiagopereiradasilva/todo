package com.tiagosilva.todo.domain;

import com.tiagosilva.todo.enums.EnumTaskStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private EnumTaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
