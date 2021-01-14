package com.dicualinleon.MusicShop.dto.products;

import com.dicualinleon.MusicShop.utils.ProductTypes;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class StringsDto extends ProductDto {

    @NotNull
    final private double gauge;
    final private long productId;

    @Builder
    public StringsDto(long id,
                      String name,
                      double price,
                      String description,
                      String producer,
                      int quantity,
                      double gauge,
                      long productId) {
        super(id, name, price, description, producer, ProductTypes.STRINGS, quantity);
        this.gauge = gauge;
        this.productId = productId;
    }
}
