package ru.ekudashov.taskms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.CrossOriginEmbedderPolicyHeaderWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ekudashov.taskms.dto.UserRequestDto;
import ru.ekudashov.taskms.mapper.UserMapper;
import ru.ekudashov.taskms.model.User;
import ru.ekudashov.taskms.repository.UserRepository;
import ru.ekudashov.taskms.service.UserService;

@RestController
@RequestMapping(path = "/api/1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserRequestDto userRequestDto) {

        userService.createUser(userRequestDto);
    }

}
