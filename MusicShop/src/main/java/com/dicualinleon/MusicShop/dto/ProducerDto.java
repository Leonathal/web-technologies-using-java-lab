package com.dicualinleon.MusicShop.dto;

public class ProducerDto {

    //region Data members
    private String name;
    private String description;
    //endregion

    //region Constructors
    public ProducerDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
    //endregion

    //region Getters / Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription() {
        this.description = description;
    }
    //endregion
}
