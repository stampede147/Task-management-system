package ru.ekudashov.taskms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class EmailPasswordRequestDto {

    @Email
    String email;

    @NotEmpty
    String password;
}
