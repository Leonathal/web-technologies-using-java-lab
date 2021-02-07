package com.dicualinleon.exam.controller;

import com.dicualinleon.exam.dto.BikeDto;
import com.dicualinleon.exam.exception.BadBikeTypeException;
import com.dicualinleon.exam.mapper.BikeMapper;
import com.dicualinleon.exam.model.Bike;
import com.dicualinleon.exam.service.BikeService;
import com.dicualinleon.exam.utils.BikeType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    private BikeService bikeService;
    private BikeMapper bikeMapper;

    public BikeController(BikeService bikeService,
                          BikeMapper bikeMapper) {
        this.bikeService = bikeService;
        this.bikeMapper = bikeMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bike> addABicycle(
            @RequestBody
            @Valid
            BikeDto bikeDto
    ) {
        Bike savedBike = bikeService.createBike(bikeMapper.bikeDtoToBike(bikeDto));
        return ResponseEntity
                .created(URI.create("/" + savedBike.getId()))
                .body(savedBike);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Bike>> getAllBicyclesByType(
            @RequestParam(name = "type", required = false)
            String strBikeType,
            @RequestParam(name = "brand", required = false)
            String brandName
    ) {
        BikeType bikeType = null;
        if(strBikeType != null) {
            bikeType = BikeType.fromString(strBikeType);
            if (bikeType == null) {
                throw new BadBikeTypeException();
            }
        }
        List<Bike> bikes = bikeService.getAll(bikeType, brandName);
        return ResponseEntity
                .ok()
                .body(bikes);
    }
}
