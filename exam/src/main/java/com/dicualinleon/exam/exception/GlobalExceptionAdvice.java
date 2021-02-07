package com.dicualinleon.exam.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler({RoadBikePriceException.class})
    public ResponseEntity handle(RoadBikePriceException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({MountainBikePriceException.class})
    public ResponseEntity handle(MountainBikePriceException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({TouringBikePriceException.class})
    public ResponseEntity handle(TouringBikePriceException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({BadBikeTypeException.class})
    public ResponseEntity handle(BadBikeTypeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
