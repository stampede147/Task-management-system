package ru.ekudashov.taskms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.ekudashov.taskms.dto.PageDto;
import ru.ekudashov.taskms.dto.TaskRequestDto;
import ru.ekudashov.taskms.dto.TaskResponseDto;
import ru.ekudashov.taskms.service.TaskService;

@RestController
@RequestMapping(path = "/api/1/tasks")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public final class TaskController {

    private final TaskService taskService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponseDto createTask(@RequestBody TaskRequestDto taskRequestDto) {

        return taskService.createTask(taskRequestDto);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponseDto updateTask(@PathVariable long id, TaskRequestDto taskRequestDto) {

        return taskService.updateTask(id, taskRequestDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteTask(@PathVariable @NonNull Long id) {

        taskService.deleteTaskById(id);
    }

    @GetMapping(path = "/{id}")
    public TaskResponseDto getTaskById(@PathVariable @NonNull Long id) {

        return taskService.findTaskById(id);
    }

    @PostMapping(path = "/search")
    public PageDto<TaskResponseDto> searchTasks(@RequestBody TaskRequestDto taskRequestDto, @PageableDefault Pageable pageable) {

        return taskService.findTasks(taskRequestDto, pageable);
    }

}