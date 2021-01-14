package com.dicualinleon.MusicShop.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Order {

    final private long id;
    final private long userId;
    final private long addressId;
    final private String status;
}
