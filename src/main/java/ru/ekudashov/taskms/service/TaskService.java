package ru.ekudashov.taskms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.ekudashov.taskms.dto.PageDto;
import ru.ekudashov.taskms.dto.TaskRequestDto;
import ru.ekudashov.taskms.dto.TaskResponseDto;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto taskRequestDto);

    TaskResponseDto updateTask(TaskRequestDto taskRequestDto);

    void deleteTaskById(long taskId);

    TaskResponseDto findTaskById(long taskId);

    PageDto<TaskResponseDto> findTasks(TaskRequestDto taskRequestDto, Pageable pageable);


}
