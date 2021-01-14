package com.dicualinleon.MusicShop.dto.products;

import com.dicualinleon.MusicShop.utils.GuitarTypes;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class GuitarDto extends ProductDto {

    @NotNull
    final private GuitarTypes guitarType;

    @Builder
    public GuitarDto(int id,
                     String name,
                     double price,
                     String description,
                     String producer,
                     GuitarTypes guitarType,
                     int quantity) {
        super(id, name, price, description, producer, ProductTypes.GUITAR, quantity);
        this.guitarType = guitarType;
    }
}
