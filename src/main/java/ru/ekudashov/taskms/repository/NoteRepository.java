package ru.ekudashov.taskms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ekudashov.taskms.model.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    public List<Note> findAllByTaskId(long taskId);
}
