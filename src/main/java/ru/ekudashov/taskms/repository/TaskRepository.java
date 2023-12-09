package ru.ekudashov.taskms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ekudashov.taskms.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
