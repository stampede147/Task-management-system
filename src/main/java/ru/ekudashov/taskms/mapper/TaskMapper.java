package ru.ekudashov.taskms.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import ru.ekudashov.taskms.dto.PageDto;
import ru.ekudashov.taskms.dto.TaskRequestDto;
import ru.ekudashov.taskms.dto.TaskResponseDto;
import ru.ekudashov.taskms.model.Task;
import ru.ekudashov.taskms.repository.UserRepository;

@Mapper(componentModel = "spring", uses = PageMapper.class)
public abstract class TaskMapper {

    @Mapping(target = "creator", expression = "java(userRepository.getReferenceById(taskRequestDto.creator))")
    @Mapping(target = "performer", expression = "java(userRepository.getReferenceById(taskRequestDto.performer))")
    public abstract Task toTask(TaskRequestDto taskRequestDto, UserRepository userRepository);

    public abstract TaskResponseDto toTaskResponseDto(Task task);

    public abstract PageDto<TaskResponseDto> toTaskResponseDto(Page<Task> tasks);
}
