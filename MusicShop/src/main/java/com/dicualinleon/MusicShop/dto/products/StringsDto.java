package com.dicualinleon.MusicShop.dto.products;

import com.dicualinleon.MusicShop.utils.ProductTypes;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StringsDto extends ProductDto {

    @Builder
    public StringsDto(int id,
                      String name,
                      double price,
                      String description,
                      String producer,
                      int quantity) {
        super(id, name, price, description, producer, ProductTypes.STRINGS, quantity);
    }
}
