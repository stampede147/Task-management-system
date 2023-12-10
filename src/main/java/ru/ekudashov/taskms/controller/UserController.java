package ru.ekudashov.taskms.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ekudashov.taskms.dto.UserRequestDto;
import ru.ekudashov.taskms.service.UserService;

@Tag(name = "User", description = "Provides api about users")
@RestController
@RequestMapping(path = "/api/1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(tags = {"User", "Registration"}, description = "Creates the user")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@Validated @RequestBody UserRequestDto userRequestDto) {

        userService.createUser(userRequestDto);
    }

}
