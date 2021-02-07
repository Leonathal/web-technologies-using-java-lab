package com.dicualinleon.exam.utils;

import java.util.HashMap;
import java.util.Map;

public enum BikeType {
    Road(0),
    Mountain(1),
    Touring(2);

    private final int value;
    private static Map<Integer, BikeType> bikeIntValuesToEnumValuesMapping = new HashMap<>();
    private static Map<String, BikeType> bikeNameValuesToEnumValuesMapping = new HashMap<>();

    private BikeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    static {
        for(BikeType bikeType : BikeType.values()) {
            bikeIntValuesToEnumValuesMapping.put(
                    bikeType.getValue(),
                    bikeType
            );
        }

        bikeNameValuesToEnumValuesMapping.put("Road", Road);
        bikeNameValuesToEnumValuesMapping.put("Mountain", Mountain);
        bikeNameValuesToEnumValuesMapping.put("Touring", Touring);
    }

    public static BikeType fromInt(int intBikeType) {
        return bikeIntValuesToEnumValuesMapping.get(intBikeType);
    }

    public static BikeType fromString(String strBikeType) {
        return bikeNameValuesToEnumValuesMapping.get(strBikeType);
    }
}
