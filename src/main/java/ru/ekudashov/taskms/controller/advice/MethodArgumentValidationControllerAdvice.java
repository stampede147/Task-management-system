package ru.ekudashov.taskms.controller.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
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

        FieldError fieldError = e.getFieldError();
        adviceResponse.setMessage(String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage()));

        return adviceResponse;
    }

}
