package ru.ydubovitsky.grammarrushspring.service;

import ru.ydubovitsky.grammarrushspring.dto.TaskRequestDto;
import ru.ydubovitsky.grammarrushspring.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task addNewTask(TaskRequestDto taskRequestDto);

}
