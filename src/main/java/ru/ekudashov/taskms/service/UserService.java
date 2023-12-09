package ru.ekudashov.taskms.service;

import ru.ekudashov.taskms.dto.UserRequestDto;

public interface UserService {

    public void createUser(UserRequestDto userRequestDto);
}
