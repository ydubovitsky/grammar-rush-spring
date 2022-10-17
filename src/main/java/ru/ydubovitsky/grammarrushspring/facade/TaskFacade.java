package ru.ydubovitsky.grammarrushspring.facade;

import ru.ydubovitsky.grammarrushspring.dto.TaskResponseDto;
import ru.ydubovitsky.grammarrushspring.entity.Task;

public class TaskFacade {

    public static TaskResponseDto taskToTaskResponseDto(Task task) {
        return TaskResponseDto.builder()
                .id(task.getId())
                .answer(task.getAnswer())
                .task(task.getTask())
                .themeName(task.getTheme().getName())
                .build();
    }

    public static Task taskResponseDtoToTask(TaskResponseDto taskResponseDto) {
        return Task.builder()
                .id(taskResponseDto.getId())
                .answer(taskResponseDto.getAnswer())
                .task(taskResponseDto.getTask())
                .build();
    }

}
