package com.dicualinleon.exam.service;

import com.dicualinleon.exam.exception.MountainBikePriceException;
import com.dicualinleon.exam.exception.RoadBikePriceException;
import com.dicualinleon.exam.exception.TouringBikePriceException;
import com.dicualinleon.exam.model.Bike;
import com.dicualinleon.exam.repository.BikeRepository;
import com.dicualinleon.exam.utils.BikeType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    private BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike createBike(Bike bike) {
        double price = bike.getPrice();
        switch(bike.getType())
        {
            case Road:
                if(price > 15)
                    throw new RoadBikePriceException();
                break;
            case Mountain:
                if(price < 20)
                    throw new MountainBikePriceException();
                break;
            case Touring:
                if(price < 25)
                    throw new TouringBikePriceException();
                break;
        }
        return bikeRepository.createBike(bike);
    }

    public List<Bike> getAll(BikeType type, String brandName) {
        List<Bike> bikes = bikeRepository.getAll(type, brandName);
        return bikes;
    }
}
