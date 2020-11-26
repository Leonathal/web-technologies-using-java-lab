package com.dicualinleon.MusicShop.dto;

import com.dicualinleon.MusicShop.utils.GuitarTypes;

public class GuitarDto extends ProductDto {

    private GuitarTypes type;

    public GuitarDto(String name, double price, String description, ProducerDto producer, GuitarTypes type) {
        super(name, price, description, producer);
        this.type = type;
    }

    public GuitarTypes getType() {
        return type;
    }

    public void setType(GuitarTypes type) {
        this.type = type;
    }
}
