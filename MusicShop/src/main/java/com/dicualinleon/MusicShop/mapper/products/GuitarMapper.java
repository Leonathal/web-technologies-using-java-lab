package com.dicualinleon.MusicShop.mapper.products;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.dto.products.GuitarDto;
import com.dicualinleon.MusicShop.mapper.ProducerMapper;
import org.springframework.stereotype.Component;

@Component
public class GuitarMapper {

    private ProducerMapper producerMapper;

    public GuitarMapper(ProducerMapper producerMapper) {
        this.producerMapper = producerMapper;
    }

    public Guitar toEntity(GuitarDto guitarDto) {
        return Guitar.builder()
                .name(guitarDto.getName())
                .price(guitarDto.getPrice())
                .description(guitarDto.getDescription())
                .producer(producerMapper.toEntity(guitarDto.getProducer()))
                .guitarType(guitarDto.getGuitarType())
                .quantity(guitarDto.getQuantity())
                .build();
    }

    public GuitarDto toDto(Guitar guitar) {
        return GuitarDto.builder()
                .name(guitar.getName())
                .price(guitar.getPrice())
                .description(guitar.getDescription())
                .producer(producerMapper.toDto(guitar.getProducer()))
                .guitarType(guitar.guitarType())
                .quantity(guitar.getQuantity())
                .build();
    }
}
