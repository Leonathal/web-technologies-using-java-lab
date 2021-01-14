package com.dicualinleon.MusicShop.exception;

public class GuitarNotFoundException extends RuntimeException {

    public GuitarNotFoundException(long id) {
        super("Guitar with id:" + id + " was not found");
    }
}
