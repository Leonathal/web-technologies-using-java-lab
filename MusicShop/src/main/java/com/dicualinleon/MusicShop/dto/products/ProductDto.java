package com.dicualinleon.MusicShop.dto.products;

import com.dicualinleon.MusicShop.dto.ProducerDto;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ProductDto {

    final private String name;
    final private double price;
    final private String description;
    final private ProducerDto producer;
    final private ProductTypes type;
}
