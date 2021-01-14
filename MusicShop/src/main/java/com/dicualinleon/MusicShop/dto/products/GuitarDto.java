package com.dicualinleon.MusicShop.dto.products;

import com.dicualinleon.MusicShop.utils.GuitarTypes;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GuitarDto extends ProductDto {

    final private GuitarTypes guitarType;

    @Builder
    public GuitarDto(String name,
                     double price,
                     String description,
                     String producer,
                     GuitarTypes guitarType,
                     int quantity) {
        super(name, price, description, producer, ProductTypes.GUITAR, quantity);
        this.guitarType = guitarType;
    }
}
