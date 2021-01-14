package com.dicualinleon.MusicShop.exception;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(long id) {
        super("Product with id " + id + " is out of stock");
    }
}
