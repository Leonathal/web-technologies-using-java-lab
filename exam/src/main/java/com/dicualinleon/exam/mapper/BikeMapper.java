package com.dicualinleon.exam.mapper;

import com.dicualinleon.exam.dto.BikeDto;
import com.dicualinleon.exam.model.Bike;
import org.springframework.stereotype.Component;

@Component
public class BikeMapper {

    public Bike bikeDtoToBike(BikeDto bikeDto) {
        return new Bike(bikeDto.getBrand(),
                bikeDto.getModel(),
                bikeDto.getPrice(),
                bikeDto.getColor(),
                bikeDto.getType());
    }

    public BikeDto bikeToBikeDto(Bike bike) {
        return new BikeDto(bike.getBrand(),
                bike.getModel(),
                bike.getPrice(),
                bike.getColor(),
                bike.getType());
    }
}
