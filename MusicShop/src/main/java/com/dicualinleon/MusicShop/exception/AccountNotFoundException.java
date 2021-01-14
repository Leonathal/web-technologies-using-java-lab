package com.dicualinleon.MusicShop.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(long id) {
        super("The account with id " + id + " was not found");
    }

    public AccountNotFoundException(String username, String password) {
        super("The account with username:" + username + " and password:" + password + " could not be found");
    }
}
