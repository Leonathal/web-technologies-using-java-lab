package com.dicualinleon.MusicShop.exception;

public class OrderProductNotCreatedException extends RuntimeException {

    public OrderProductNotCreatedException(long orderId, long productId) {
        super("Could not add the product " + productId + " in order " + orderId);
    }
}
