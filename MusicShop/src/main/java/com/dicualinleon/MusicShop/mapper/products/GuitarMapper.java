package com.dicualinleon.MusicShop.mapper.products;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.dto.products.GuitarDto;
import org.springframework.stereotype.Component;

@Component
public class GuitarMapper {

    public Guitar toEntity(GuitarDto guitarDto) {
        return Guitar.builder()
                .id(guitarDto.getId())
                .name(guitarDto.getName())
                .price(guitarDto.getPrice())
                .description(guitarDto.getDescription())
                .producer(guitarDto.getProducer())
                .guitarType(guitarDto.getGuitarType())
                .quantity(guitarDto.getQuantity())
                .productId(guitarDto.getProductId())
                .build();
    }

    public GuitarDto toDto(Guitar guitar) {
        return GuitarDto.builder()
                .id(guitar.getId())
                .name(guitar.getName())
                .price(guitar.getPrice())
                .description(guitar.getDescription())
                .producer(guitar.getProducer())
                .guitarType(guitar.guitarType())
                .quantity(guitar.getQuantity())
                .productId(guitar.getProductId())
                .build();
    }
}
