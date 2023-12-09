package ru.ekudashov.taskms.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ekudashov.taskms.dto.UserRequestDto;
import ru.ekudashov.taskms.model.Role;
import ru.ekudashov.taskms.model.User;

import java.util.Set;

@Mapper(componentModel = "spring", imports = {Set.class, Role.class})
public abstract class UserMapper {

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(userRequestDto.getPassword()))")
    @Mapping(target = "authorities", expression = "java(Set.of(Role.USER))")
    public abstract User toUser(UserRequestDto userRequestDto, PasswordEncoder passwordEncoder);

}
