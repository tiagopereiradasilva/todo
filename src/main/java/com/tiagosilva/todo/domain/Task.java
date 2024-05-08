package com.tiagosilva.todo.domain;

import com.tiagosilva.todo.enums.EnumTaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id@GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String description;
    private EnumTaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
