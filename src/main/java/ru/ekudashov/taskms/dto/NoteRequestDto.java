package ru.ekudashov.taskms.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public class NoteRequestDto {

    @Positive
    public long taskId;

    @NotEmpty
    public String note;
}
