package ru.ekudashov.taskms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ekudashov.taskms.dto.PageDto;
import ru.ekudashov.taskms.dto.TaskRequestDto;
import ru.ekudashov.taskms.dto.TaskResponseDto;
import ru.ekudashov.taskms.mapper.TaskMapper;
import ru.ekudashov.taskms.model.Task;
import ru.ekudashov.taskms.repository.TaskRepository;
import ru.ekudashov.taskms.repository.UserRepository;
import ru.ekudashov.taskms.service.TaskService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {

        Task task = taskMapper.toTask(taskRequestDto, userRepository);

        Task savedTask = taskRepository.save(task);

        return taskMapper.toTaskResponseDto(savedTask);
    }

    @Override
    @Transactional
    public TaskResponseDto updateTask(TaskRequestDto taskRequestDto) {

        Task task = taskMapper.toTask(taskRequestDto, userRepository);

        Task saved = taskRepository.save(task);

        return taskMapper.toTaskResponseDto(saved);
    }

    @Override
    @Transactional
    public void deleteTaskById(long taskId) {

        Task task = taskRepository.getReferenceById(taskId);
        taskRepository.delete(task);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDto findTaskById(long taskId) {

        Task task = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);

        return taskMapper.toTaskResponseDto(task);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<TaskResponseDto> findTasks(TaskRequestDto taskRequestDto, Pageable pageable) {

        Task task = taskMapper.toTask(taskRequestDto, userRepository);

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues();
        Page<Task> page = taskRepository.findAll(Example.of(task, matcher), pageable);

        return taskMapper.toTaskResponseDto(page);
    }
}
