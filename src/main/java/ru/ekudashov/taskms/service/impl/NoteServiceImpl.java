package ru.ekudashov.taskms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ekudashov.taskms.dto.NoteRequestDto;
import ru.ekudashov.taskms.dto.NoteResponseDto;
import ru.ekudashov.taskms.mapper.NoteMapper;
import ru.ekudashov.taskms.model.Note;
import ru.ekudashov.taskms.repository.NoteRepository;
import ru.ekudashov.taskms.service.NoteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    @Override
    @Transactional
    public NoteResponseDto createNote(NoteRequestDto noteRequestDto) {

        return noteMapper.toNoteResponseDto(noteRepository.save(noteMapper.toNote(noteRequestDto)));
    }

    @Override
    @Transactional
    public void deleteNoteById(long taskNoteId) {

        noteRepository.deleteById(taskNoteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponseDto> getNotesByTaskId(long taskId) {

        List<Note> taskNotes = noteRepository.findAllByTaskId(taskId);
        return noteMapper.toNoteResponseDto(taskNotes);
    }
}
