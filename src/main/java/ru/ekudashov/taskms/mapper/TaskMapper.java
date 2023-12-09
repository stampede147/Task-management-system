package ru.ekudashov.taskms.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import ru.ekudashov.taskms.dto.PageDto;
import ru.ekudashov.taskms.dto.TaskRequestDto;
import ru.ekudashov.taskms.dto.TaskResponseDto;
import ru.ekudashov.taskms.model.Task;
import ru.ekudashov.taskms.repository.UserRepository;

@Mapper(componentModel = "spring", uses = PageMapper.class)
public abstract class TaskMapper {

    @Mapping(target = "creator", expression = "java(taskRequestDto.creator == 0 ? null : userRepository.getReferenceById(taskRequestDto.creator))")
    @Mapping(target = "performer", expression = "java(taskRequestDto.performer == 0 ? null : userRepository.getReferenceById(taskRequestDto.performer))")
    public abstract Task toTask(TaskRequestDto taskRequestDto, UserRepository userRepository);

    @Mapping(target = "performer", source = "performer.id")
    @Mapping(target = "creator", source = "creator.id")
    public abstract TaskResponseDto toTaskResponseDto(Task task);

    @InheritConfiguration
    public abstract Task mergeTask(@MappingTarget Task mergeable, TaskRequestDto taskRequestDto, UserRepository userRepository);

    public abstract PageDto<TaskResponseDto> toTaskResponseDto(Page<Task> tasks);
}
