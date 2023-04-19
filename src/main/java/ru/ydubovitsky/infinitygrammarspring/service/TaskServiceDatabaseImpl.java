package ru.ydubovitsky.infinitygrammarspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ydubovitsky.infinitygrammarspring.dto.TaskRequestDto;
import ru.ydubovitsky.infinitygrammarspring.entity.Task;
import ru.ydubovitsky.infinitygrammarspring.entity.Theme;
import ru.ydubovitsky.infinitygrammarspring.facade.TaskFacade;
import ru.ydubovitsky.infinitygrammarspring.repository.TaskRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Transactional
@Service
@Primary
@Qualifier("TaskServiceDatabaseImpl")
@AllArgsConstructor
public class TaskServiceDatabaseImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ThemeService themeService;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task addNewTask(TaskRequestDto taskRequestDto) {
        Theme theme = themeService.findByName(taskRequestDto.getThemeName());
        if (Objects.isNull(theme)) {
            //! create and save new theme
            theme = themeService.addNewTheme(Theme.builder()
                    .themeName(taskRequestDto.getThemeName())
                    .build());
        }
        Task task = TaskFacade.taskRequestDtoToTask(taskRequestDto);
        task.setTheme(theme);
        Task savedTask = taskRepository.save(task);
        //TODO Написать обертку для логирования!
        log.info("Task saved");
        return savedTask;
    }
}
