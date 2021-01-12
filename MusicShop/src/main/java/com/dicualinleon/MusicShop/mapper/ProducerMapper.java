package com.dicualinleon.MusicShop.mapper;

import com.dicualinleon.MusicShop.domain.Producer;
import com.dicualinleon.MusicShop.dto.ProducerDto;
import org.springframework.stereotype.Component;

@Component
public class ProducerMapper {

    public Producer toEntity(ProducerDto producerDto) {
        if(producerDto == null)
            return null;

        return Producer.builder()
                .name(producerDto.getName())
                .description(producerDto.getDescription())
                .build();
    }

    public ProducerDto toDto(Producer producer) {
        if(producer == null)
            return null;

        return ProducerDto.builder()
                .name(producer.getName())
                .description(producer.getDescription())
                .build();
    }
}
