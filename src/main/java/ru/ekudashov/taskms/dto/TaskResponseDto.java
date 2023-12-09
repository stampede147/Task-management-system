package ru.ekudashov.taskms.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import ru.ekudashov.taskms.model.TaskPriority;
import ru.ekudashov.taskms.model.TaskStatus;

@Data
public class TaskResponseDto {

    @NotEmpty
    public String title;

    @NotEmpty
    public String description;

    @NotNull
    public TaskStatus status;

    @NotNull
    public TaskPriority priority;

    @Positive
    public long creator;

    @Positive
    public long performer;

}
