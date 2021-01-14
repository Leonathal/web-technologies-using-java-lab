package com.dicualinleon.MusicShop.exception;

import com.dicualinleon.MusicShop.domain.Account;

public class AccountNotCreatedException extends RuntimeException {

    public AccountNotCreatedException(Account account) {
        super("Account could not be created");
    }
}
