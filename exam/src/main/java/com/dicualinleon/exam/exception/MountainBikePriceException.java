package com.dicualinleon.exam.exception;

public class MountainBikePriceException extends RuntimeException {
    public MountainBikePriceException() {
        super("The price for a mountain bike can not be less than 20!");
    }
}
