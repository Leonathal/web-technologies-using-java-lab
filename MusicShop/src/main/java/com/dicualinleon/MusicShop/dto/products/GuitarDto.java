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
    final private long productId;

    @Builder
    public GuitarDto(long id,
                     String name,
                     double price,
                     String description,
                     String producer,
                     GuitarTypes guitarType,
                     int quantity,
                     long productId) {
        super(id, name, price, description, producer, ProductTypes.GUITAR, quantity);
        this.guitarType = guitarType;
        this.productId = productId;
    }
}
