package ru.ydubovitsky.grammarrushspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.grammarrushspring.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {


}
