package com.dicualinleon.exam.exception;

public class BadBikeTypeException extends RuntimeException {
    public BadBikeTypeException() {
        super("The bike type provided is invalid!");
    }
}
