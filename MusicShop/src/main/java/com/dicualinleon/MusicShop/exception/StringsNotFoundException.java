package com.dicualinleon.MusicShop.exception;

public class StringsNotFoundException extends RuntimeException {

    public StringsNotFoundException(long id) {
        super("Strings with id:" + id + " was not found");
    }
}
