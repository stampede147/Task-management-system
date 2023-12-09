package ru.ekudashov.taskms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessTokenResponseDto {

    public String token;
}
