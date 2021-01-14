package com.dicualinleon.MusicShop.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
public class AddressDto {

    final private long id;

    @NotBlank
    @Size(max = 45)
    final private String country;

    @NotBlank
    @Size(max = 100)
    final private String address;
}
