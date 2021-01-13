package com.dicualinleon.MusicShop.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
public class AccountDto {

    @NotNull(message = "The username field is required.")
    @Size(min = 2, max = 30)
    final private String username;

    @NotNull(message = "The password field is required.")
    @Size(min = 2, max = 30)
    final private String password;

    @NotNull(message = "The email field is required.")
    final private String email;
}
