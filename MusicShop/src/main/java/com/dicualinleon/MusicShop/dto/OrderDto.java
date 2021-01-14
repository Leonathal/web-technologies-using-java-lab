package com.dicualinleon.MusicShop.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class OrderDto {

    @NotNull
    final private long id;

    @NotNull
    final private long userId;

    @NotNull
    final private long addressId;

    @NotBlank
    final private String status;
}
