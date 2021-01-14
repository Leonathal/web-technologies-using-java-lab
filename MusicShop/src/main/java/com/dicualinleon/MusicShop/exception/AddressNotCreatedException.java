package com.dicualinleon.MusicShop.exception;

import com.dicualinleon.MusicShop.domain.Address;

public class AddressNotCreatedException extends RuntimeException{

    public AddressNotCreatedException(Address account) {
        super("Account could not be created");
    }
}
