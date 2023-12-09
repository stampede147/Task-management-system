package ru.ekudashov.taskms.service;

import ru.ekudashov.taskms.dto.AccessTokenResponseDto;
import ru.ekudashov.taskms.dto.EmailPasswordRequestDto;

public interface AuthenticationService {


    AccessTokenResponseDto authenticate(EmailPasswordRequestDto emailPasswordRequestDto);
}
