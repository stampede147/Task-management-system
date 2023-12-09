package ru.ekudashov.taskms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ekudashov.taskms.dto.AccessTokenResponseDto;
import ru.ekudashov.taskms.dto.EmailPasswordRequestDto;
import ru.ekudashov.taskms.service.AuthenticationService;

@RestController
@RequiredArgsConstructor()
@RequestMapping()
public class AuthenticateController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AccessTokenResponseDto authenticate(@Validated @RequestBody EmailPasswordRequestDto requestDto) {

        return authenticationService.authenticate(requestDto);
    }
}
