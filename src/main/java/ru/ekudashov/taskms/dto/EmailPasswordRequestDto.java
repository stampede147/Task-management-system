package ru.ekudashov.taskms.dto;

import lombok.Data;

@Data
public class EmailPasswordRequestDto {

    String email;

    String password;
}
