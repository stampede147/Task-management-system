package ru.ekudashov.taskms.controller.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MethodArgumentValidationControllerAdvice {

    @Data
    protected static class AdviceResponse {

        String message;
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public AdviceResponse handleMethodArgumentValidationException(MethodArgumentNotValidException e) {

        AdviceResponse adviceResponse = new AdviceResponse();

        adviceResponse.setMessage(String.format("%s %s", e.getFieldError().getField(), e.getFieldError().getDefaultMessage()));

        return adviceResponse;
    }

}
