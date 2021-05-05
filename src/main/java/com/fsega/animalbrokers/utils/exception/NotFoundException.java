package com.fsega.animalbrokers.utils.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private final String message;
    private final ExceptionType exceptionType;

}
