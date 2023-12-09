package ru.ekudashov.taskms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ekudashov.taskms.dto.UserRequestDto;
import ru.ekudashov.taskms.mapper.UserMapper;
import ru.ekudashov.taskms.model.User;
import ru.ekudashov.taskms.repository.UserRepository;
import ru.ekudashov.taskms.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserRequestDto userRequestDto) {

        userRepository.save(userMapper.toUser(userRequestDto, passwordEncoder));
    }
}
