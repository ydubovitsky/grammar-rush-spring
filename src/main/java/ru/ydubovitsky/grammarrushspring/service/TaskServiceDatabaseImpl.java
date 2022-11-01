package ru.ydubovitsky.grammarrushspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ydubovitsky.grammarrushspring.dto.TaskRequestDto;
import ru.ydubovitsky.grammarrushspring.entity.Task;
import ru.ydubovitsky.grammarrushspring.entity.Theme;
import ru.ydubovitsky.grammarrushspring.facade.TaskFacade;
import ru.ydubovitsky.grammarrushspring.repository.TaskRepository;

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
        Theme theme = themeService.findByName(taskRequestDto.getTheme());
        if (Objects.isNull(theme)) {
            //! create and save new theme
            theme = themeService.addNewTheme(Theme.builder()
                    .name(taskRequestDto.getTheme())
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
