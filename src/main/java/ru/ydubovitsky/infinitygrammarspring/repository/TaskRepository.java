package ru.ydubovitsky.infinitygrammarspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.infinitygrammarspring.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {


}
