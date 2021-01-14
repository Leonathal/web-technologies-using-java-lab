package com.dicualinleon.MusicShop.exception;

import com.dicualinleon.MusicShop.domain.Order;

public class OrderNotCreatedException extends RuntimeException {

    public OrderNotCreatedException(Order order) {
        super("Order for user " + order.getUserId() + " not created");
    }
}
