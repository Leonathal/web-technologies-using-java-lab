package com.dicualinleon.exam.exception;

public class RoadBikePriceException extends RuntimeException {
    public RoadBikePriceException() {
        super("The price for a road bike must exceed 15!");
    }
}
