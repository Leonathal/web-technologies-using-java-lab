package com.dicualinleon.MusicShop.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountDto {

    final private String username;
    final private String password;
    final private String email;
}
