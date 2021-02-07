package com.dicualinleon.exam.model;

import com.dicualinleon.exam.utils.BikeType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bike {

    private long id;
    private String brand;
    private String model;
    private double price;
    private String color;
    private BikeType type;

    public Bike(long id, Bike bike) {
        this.id = id;
        this.brand = bike.brand;
        this.model = bike.model;
        this.price = bike.price;
        this.color = bike.color;
        this.type = bike.type;
    }

    public Bike(String brand, String model, double price, String color, BikeType type) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.color = color;
        this.type = type;
    }
}
