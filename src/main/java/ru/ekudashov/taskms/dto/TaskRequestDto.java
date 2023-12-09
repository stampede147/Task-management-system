package ru.ekudashov.taskms.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import ru.ekudashov.taskms.model.TaskPriority;
import ru.ekudashov.taskms.model.TaskStatus;
import ru.ekudashov.taskms.model.User;

@Data
public class TaskRequestDto {

    public String title;

    public String description;

    public TaskStatus status;

    public TaskPriority priority;

    public long creator;

    public long performer;
}
