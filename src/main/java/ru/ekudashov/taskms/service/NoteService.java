package ru.ekudashov.taskms.service;

import org.springframework.data.domain.Page;
import ru.ekudashov.taskms.dto.NoteRequestDto;
import ru.ekudashov.taskms.dto.NoteResponseDto;

import java.util.List;

public interface NoteService {

    public NoteResponseDto createNote(NoteRequestDto noteRequestDto);

    public void deleteNoteById(long taskNoteId);

    public List<NoteResponseDto> getNotesByTaskId(long taskId);
}
