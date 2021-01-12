package com.dicualinleon.MusicShop.dto.products;

import com.dicualinleon.MusicShop.dto.ProducerDto;
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
                     ProducerDto producer,
                     GuitarTypes guitarType) {
        super(name, price, description, producer, ProductTypes.GUITAR);
        this.guitarType = guitarType;
    }
}