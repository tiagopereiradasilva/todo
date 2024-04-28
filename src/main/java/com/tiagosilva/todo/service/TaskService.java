package com.tiagosilva.todo.service;

import com.tiagosilva.todo.domain.Task;
import com.tiagosilva.todo.dto.TaskDto;
import com.tiagosilva.todo.enums.EnumTaskStatus;
import com.tiagosilva.todo.exceptions.TaskNotFoundException;
import com.tiagosilva.todo.exceptions.UpdateStatusException;
import com.tiagosilva.todo.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        log.info("Buscando todos as tarefas cadastradas.");
        return  taskRepository.findAll();
    }


    public Task save(TaskDto dto) {
        log.info("Salvando Task no banco.");
        return taskRepository.save(toTask(dto));
    }

    private Task toTask(TaskDto dto) {
        log.info("Convertendo payload em nova Task.");
        return Task.builder()
                .title(dto.title())
                .description(dto.description())
                .status(EnumTaskStatus.BACKLOG)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public void update(String id, TaskDto dto) {
        log.info("Atualizando Task - id {}", id);
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(String.format("Task %s não encontrada", id)));
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);
    }

    public void delete(String id) {
        log.info("Deletando Task - id {}", id);
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(String.format("Task %s não encontrada", id)));
        taskRepository.delete(task);
    }

    public void updateStatus(String id, EnumTaskStatus status) {
        log.info("Inicio atualização de status da Task {} para {}", id, status);
        taskRepository.findById(id)
                .map(
                        t -> {
                            if (validateUpdateStatus(t.getStatus(), status)) {
                                t.setStatus(status);
                                t.setUpdatedAt(LocalDateTime.now());
                                taskRepository.save(t);
                                return t;
                            }
                            log.error("Não foi possível atualizar Task {} para {}", id, status);
                            throw new UpdateStatusException(String.format("Erro ao alterar status %s para %s da Task %s", t.getStatus(), status, id));
                        }
                ).orElseThrow(() -> new TaskNotFoundException(String.format("Task %s não encontrada", id)));

    }

    private boolean validateUpdateStatus(EnumTaskStatus status, EnumTaskStatus statusPassed){
        log.info("Validando atualização de status da Task de {} para {}", status, statusPassed);
        switch (status){
            case BACKLOG, PAUSED -> {
                return statusPassed == EnumTaskStatus.DOING;
            }
            case DOING -> {
                return statusPassed == EnumTaskStatus.PAUSED || statusPassed == EnumTaskStatus.DONE;
            }
            default -> {
                return false;
            }
        }
    }
}
