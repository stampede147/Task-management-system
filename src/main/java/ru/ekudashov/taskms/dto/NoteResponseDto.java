package ru.ekudashov.taskms.dto;

import lombok.Data;

@Data
public class NoteResponseDto {

    public long taskId;

    public String note;
}
