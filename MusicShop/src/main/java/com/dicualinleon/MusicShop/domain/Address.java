package com.dicualinleon.MusicShop.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Address {

    final private long id;
    final private String country;
    final private String address;
}
