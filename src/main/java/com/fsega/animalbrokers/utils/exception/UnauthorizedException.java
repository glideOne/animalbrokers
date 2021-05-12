package com.fsega.animalbrokers.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {

    private final String message;
    private final ExceptionType exceptionType;

}
