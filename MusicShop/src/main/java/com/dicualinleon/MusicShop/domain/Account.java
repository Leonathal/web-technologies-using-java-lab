package com.dicualinleon.MusicShop.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {

    final private long id;
    final private String username;
    final private String password;
    final private String email;
}
