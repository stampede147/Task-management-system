package ru.ekudashov.taskms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ekudashov.taskms.dto.PageDto;
import ru.ekudashov.taskms.dto.TaskRequestDto;
import ru.ekudashov.taskms.dto.TaskResponseDto;
import ru.ekudashov.taskms.service.TaskService;

@Tag(name = "Task", description = "provides API about tasks")
@RestController
@RequestMapping(path = "/api/1/tasks")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public final class TaskController {

    private final TaskService taskService;

    @Operation(tags = {"Task"},
            description = "Creates the task.",
            parameters = @Parameter(name = "taskRequestDto", description = "the task representation") )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponseDto createTask(@Validated @RequestBody TaskRequestDto taskRequestDto) {

        return taskService.createTask(taskRequestDto);
    }

    @Operation(tags = {"Task"},
            description = "Updates the task.",
            parameters = @Parameter(name = "taskRequestDto", description = "the task representation") )
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponseDto updateTask(@Positive @PathVariable long id,
                                      @Validated @RequestBody TaskRequestDto taskRequestDto) {

        return taskService.updateTask(id, taskRequestDto);
    }

    @Operation(tags = {"Task"},
            description = "deletes the task by provided id.")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteTask(@PathVariable @Positive Long id) {

        taskService.deleteTaskById(id);
    }

    @Operation(tags = {"Task"},
            description = "returns task by provided id")
    @GetMapping(path = "/{id}")
    public TaskResponseDto getTaskById(@PathVariable @Positive Long id) {

        return taskService.findTaskById(id);
    }

    @Operation(tags = {"Task"},
            description = "Searches tasks by provided representation of task in Request Body.")
    @PostMapping(path = "/search")
    public PageDto<TaskResponseDto> searchTasks(@RequestBody TaskRequestDto taskRequestDto, @ParameterObject @PageableDefault Pageable pageable) {

        return taskService.findTasks(taskRequestDto, pageable);
    }

}