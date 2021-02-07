package com.dicualinleon.exam.dto;

import com.dicualinleon.exam.utils.BikeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BikeDto {

    @NotBlank
    @Size(max = 200)
    private String brand;

    @NotBlank
    @Size(max = 200)
    private String model;

    @Min(0)
    private double price;

    @Size(max = 50)
    private String color;

    @NotNull
    private BikeType type;
}
