package ru.ydubovitsky.infinitygrammarspring.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ydubovitsky.infinitygrammarspring.dto.TaskRequestDto;
import ru.ydubovitsky.infinitygrammarspring.dto.TaskResponseDto;
import ru.ydubovitsky.infinitygrammarspring.entity.Task;
import ru.ydubovitsky.infinitygrammarspring.facade.TaskFacade;
import ru.ydubovitsky.infinitygrammarspring.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(@Qualifier("TaskServiceDatabaseImpl") TaskService taskService) {
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

    @PostMapping("/add")
    public ResponseEntity<TaskResponseDto> addNewTask(@RequestBody TaskRequestDto taskRequestDto) {
        Task savedTask = taskService.addNewTask(taskRequestDto);
        return ResponseEntity.ok(TaskFacade.taskToTaskResponseDto(savedTask));
    }

}
