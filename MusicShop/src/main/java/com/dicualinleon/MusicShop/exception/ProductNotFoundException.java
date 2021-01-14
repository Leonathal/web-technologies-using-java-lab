package com.dicualinleon.MusicShop.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(long id) {
        super("Product with id:" + id + " was not found");
    }
}
