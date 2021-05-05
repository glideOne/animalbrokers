package com.fsega.animalbrokers.utils.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(code = BAD_REQUEST)
    @ResponseBody
    public Error badRequestException(BadRequestException e) {
        return Error.builder()
                .exceptionType(e.getExceptionType())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = NOT_FOUND)
    @ResponseBody
    public Error notFoundException(NotFoundException e) {
        return Error.builder()
                .exceptionType(e.getExceptionType())
                .message(e.getMessage())
                .build();
    }

    @Builder
    @Getter
    private static class Error {
        private ExceptionType exceptionType;
        private String message;
        private List<String> fieldErrors;
    }

}
