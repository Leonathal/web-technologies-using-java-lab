package com.dicualinleon.MusicShop.dto.products;

import com.dicualinleon.MusicShop.utils.ProductTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public abstract class ProductDto {

    @NotBlank
    final private String name;

    @NotNull
    @Min(0)
    final private double price;

    @NotBlank
    final private String description;

    @NotNull
    final private String producer;

    @NotNull
    final private ProductTypes type;

    @NotNull
    @Min(0)
    final private int quantity;
}
