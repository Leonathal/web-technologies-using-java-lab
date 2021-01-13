package com.dicualinleon.MusicShop.dto.products;

import com.dicualinleon.MusicShop.dto.ProducerDto;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PianoDto extends ProductDto {

    @Builder
    public PianoDto(String name,
                    double price,
                    String description,
                    ProducerDto producer,
                    int quantity) {
        super(name, price, description, producer, ProductTypes.PIANO, quantity);
    }
}
