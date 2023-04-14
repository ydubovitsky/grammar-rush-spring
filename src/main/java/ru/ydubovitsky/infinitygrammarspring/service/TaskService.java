package ru.ydubovitsky.infinitygrammarspring.service;

import ru.ydubovitsky.infinitygrammarspring.dto.TaskRequestDto;
import ru.ydubovitsky.infinitygrammarspring.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task addNewTask(TaskRequestDto taskRequestDto);

}
