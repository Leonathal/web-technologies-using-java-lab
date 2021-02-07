package com.dicualinleon.exam.exception;

public class TouringBikePriceException extends RuntimeException {
    public TouringBikePriceException() {
        super("The price for a touring bike can not be less than 25!");
    }
}
