package ru.ekudashov.taskms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ekudashov.taskms.dto.NoteRequestDto;
import ru.ekudashov.taskms.dto.NoteResponseDto;
import ru.ekudashov.taskms.service.NoteService;

import java.util.List;

@RequiredArgsConstructor()
@RestController
@RequestMapping("/api/1/notes")
@Tag(name = "Note", description = "provides api about task notes")
public class NoteController {

    private final NoteService noteService;

    @Operation(tags = {"Note"}, description = "Creates the note")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public NoteResponseDto createTaskNote(@Validated NoteRequestDto taskNoteRequestDto) {

        return noteService.createNote(taskNoteRequestDto);
    }

    @Operation(tags = {"Note"}, description = "Deletes the note")
    @Transactional()
    @DeleteMapping(params = "/{id}")
    public void deleteNote(@RequestParam long id) {
        noteService.deleteNoteById(id);
    }

    @Operation(tags = {"Note"},
            description = "Returns an array of notes by provided parameter task id",
            parameters = @Parameter(name = "taskId", description = "The task id"))
    @Transactional(readOnly = true)
    @GetMapping(params = "taskId", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteResponseDto> getTaskNotesByTaskId(@RequestParam long taskId) {

        return noteService.getNotesByTaskId(taskId);
    }
}
