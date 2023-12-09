package ru.ekudashov.taskms.dto;

import lombok.Data;

@Data
public class UserRequestDto {

    String firstName;

    String email;

    String password;
}
