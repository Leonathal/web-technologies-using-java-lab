package com.dicualinleon.MusicShop.exception.advice;

import com.dicualinleon.MusicShop.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<String> HandleResponseString(RuntimeException e, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(e.getMessage());
    }

    @ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<String> handle(AccountNotFoundException e) {
        return HandleResponseString(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AccountNotCreatedException.class})
    public ResponseEntity<String> handle(AccountNotCreatedException e) {
        return HandleResponseString(e, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler({AddressNotCreatedException.class})
    public ResponseEntity<String> handle(AddressNotCreatedException e) {
        return HandleResponseString(e, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<String> handle(ProductNotFoundException e) {
        return HandleResponseString(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({GuitarNotFoundException.class})
    public ResponseEntity<String> handle(GuitarNotFoundException e) {
        return HandleResponseString(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DuplicateUsernameException.class})
    public ResponseEntity<String> handle(DuplicateUsernameException e) {
        return HandleResponseString(e, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler({StringsNotFoundException.class})
    public ResponseEntity<String> handle(StringsNotFoundException e) {
        return HandleResponseString(e, HttpStatus.NOT_FOUND);
    }
}
