package ru.ekudashov.taskms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import ru.ekudashov.taskms.dto.NoteRequestDto;
import ru.ekudashov.taskms.dto.NoteResponseDto;
import ru.ekudashov.taskms.model.Note;

import java.util.List;

@Mapper(componentModel = "spring", uses = PageMapper.class)
public abstract class NoteMapper {


    @Mapping(source = "note", target = "note")
    public abstract Note toNote(NoteRequestDto requestDto);

    public abstract NoteResponseDto toNoteResponseDto(Note note);

    public abstract List<NoteResponseDto> toNoteResponseDto(List<Note> note);
}
