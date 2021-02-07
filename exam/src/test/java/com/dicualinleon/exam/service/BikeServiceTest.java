package com.dicualinleon.exam.service;

import com.dicualinleon.exam.exception.MountainBikePriceException;
import com.dicualinleon.exam.exception.RoadBikePriceException;
import com.dicualinleon.exam.exception.TouringBikePriceException;
import com.dicualinleon.exam.model.Bike;
import com.dicualinleon.exam.repository.BikeRepository;
import com.dicualinleon.exam.utils.BikeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeServiceTest {

    @Mock
    private BikeRepository bikeRepository;
    @InjectMocks
    private BikeService bikeService;

    @Test
    @DisplayName("Create bike - happy flow")
    void createBike() {
        Bike bike = new Bike("TwinBike",
                "M9",
                13,
                "Red",
                BikeType.Road);
        Bike savedbike = new Bike(1, bike);
        when(bikeRepository.createBike(any())).thenReturn(savedbike);

        Bike result = bikeService.createBike(bike);

        assertEquals(1, result.getId());
        assertEquals(bike.getBrand(), result.getBrand());
        assertEquals(bike.getModel(), result.getModel());
        assertEquals(bike.getPrice(), result.getPrice());
        assertEquals(bike.getColor(), result.getColor());
        assertEquals(bike.getType(), result.getType());

        verify(bikeRepository).createBike(any());
    }

    @Test
    @DisplayName("Create bike - Road bike exception thrown")
    void createBikeRoadBikeException() {
        Bike bike = new Bike("TwinBike",
                "M9",
                19,
                "Red",
                BikeType.Road);

        RoadBikePriceException exeption = assertThrows(
                RoadBikePriceException.class,
                () -> bikeService.createBike(bike)
        );

        assertEquals("The price for a road bike must exceed 15!",
                exeption.getMessage());
    }

    @Test
    @DisplayName("Create bike - Mountain bike exception thrown")
    void createBikeMountainBikeException() {
        Bike bike = new Bike("TwinBike",
                "M9",
                19,
                "Red",
                BikeType.Mountain);

        MountainBikePriceException exeption = assertThrows(
                MountainBikePriceException.class,
                () -> bikeService.createBike(bike)
        );

        assertEquals("The price for a mountain bike can not be less than 20!",
                exeption.getMessage());
    }

    @Test
    @DisplayName("Create bike - Touring bike exception thrown")
    void createBikeTouringBikeException() {
        Bike bike = new Bike("TwinBike",
                "M9",
                19,
                "Red",
                BikeType.Touring);

        TouringBikePriceException exeption = assertThrows(
                TouringBikePriceException.class,
                () -> bikeService.createBike(bike)
        );

        assertEquals("The price for a touring bike can not be less than 25!",
                exeption.getMessage());
    }

    @Test
    @DisplayName("Get all - Happy flow")
    void getAllByType() {
        BikeType type = BikeType.Road;
        String brandName = "TwinBike";

        Bike bike1 = new Bike("TwinBike",
                "M9",
                19,
                "Red",
                BikeType.Road);
        List<Bike> bikes = new ArrayList<>();
        bikes.add(bike1);

        when(bikeRepository.getAll(type, brandName)).thenReturn(bikes);

        List<Bike> result = bikeService.getAll(type, brandName);

        assertEquals(1, result.size());
    }
}