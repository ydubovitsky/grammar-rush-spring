package ru.ydubovitsky.infinitygrammarspring.facade;

import ru.ydubovitsky.infinitygrammarspring.dto.TaskRequestDto;
import ru.ydubovitsky.infinitygrammarspring.dto.TaskResponseDto;
import ru.ydubovitsky.infinitygrammarspring.entity.Task;

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

    public static Task taskRequestDtoToTask(TaskRequestDto taskRequestDto) {
        return Task.builder()
                .task(taskRequestDto.getTask())
                .answer(taskRequestDto.getAnswer())
                .hint(taskRequestDto.getHint())
                .build();
    }

}
