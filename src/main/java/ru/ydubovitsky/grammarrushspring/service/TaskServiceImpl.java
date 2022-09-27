package ru.ydubovitsky.grammarrushspring.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.grammarrushspring.entity.Task;
import ru.ydubovitsky.grammarrushspring.repository.TaskRepository;

import java.util.List;

@Service
@Qualifier("Database")
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}
