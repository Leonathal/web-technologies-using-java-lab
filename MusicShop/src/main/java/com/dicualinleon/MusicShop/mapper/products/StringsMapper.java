package com.dicualinleon.MusicShop.mapper.products;

import com.dicualinleon.MusicShop.domain.products.Strings;
import com.dicualinleon.MusicShop.dto.products.StringsDto;
import org.springframework.stereotype.Component;

@Component
public class StringsMapper {

    public Strings toEntity(StringsDto stringsDto) {
        return Strings.builder()
                .id(stringsDto.getId())
                .name(stringsDto.getName())
                .price(stringsDto.getPrice())
                .description(stringsDto.getDescription())
                .producer(stringsDto.getProducer())
                .gauge(stringsDto.getGauge())
                .quantity(stringsDto.getQuantity())
                .productId(stringsDto.getProductId())
                .build();
    }

    public StringsDto toDto(Strings strings) {
        return StringsDto.builder()
                .id(strings.getId())
                .name(strings.getName())
                .price(strings.getPrice())
                .description(strings.getDescription())
                .producer(strings.getProducer())
                .quantity(strings.getQuantity())
                .gauge(strings.getGauge())
                .productId(strings.getProductId())
                .build();
    }
}
