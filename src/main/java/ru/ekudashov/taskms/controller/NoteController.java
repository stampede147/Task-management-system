package ru.ekudashov.taskms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ekudashov.taskms.dto.NoteRequestDto;
import ru.ekudashov.taskms.dto.NoteResponseDto;
import ru.ekudashov.taskms.service.NoteService;

import java.util.List;

@RequiredArgsConstructor()
@RestController
@RequestMapping("/api/1/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public NoteResponseDto createTaskNote(NoteRequestDto taskNoteRequestDto) {

        return noteService.createNote(taskNoteRequestDto);
    }

    @Transactional()
    @DeleteMapping(params = "/{id}")
    public void deleteNote(@RequestParam long id) {
        noteService.deleteNoteById(id);
    }

    @Transactional(readOnly = true)
    @GetMapping(params = "taskId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteResponseDto> getTaskNotesByTaskId(@RequestParam long taskId) {

        return noteService.getNotesByTaskId(taskId);
    }
}
