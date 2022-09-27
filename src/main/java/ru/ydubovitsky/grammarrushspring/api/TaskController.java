package ru.ydubovitsky.grammarrushspring.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.ydubovitsky.grammarrushspring.dto.TaskResponseDto;
import ru.ydubovitsky.grammarrushspring.entity.Task;
import ru.ydubovitsky.grammarrushspring.facade.TaskFacade;
import ru.ydubovitsky.grammarrushspring.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(@Qualifier("MockTaskService") TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        if(allTasks.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tasks not found");
        }
        List<TaskResponseDto> response = allTasks.stream()
                .map(task -> TaskFacade.taskToTaskResponseDto(task))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

}
