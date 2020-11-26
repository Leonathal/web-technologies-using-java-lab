package com.dicualinleon.MusicShop.dto;

public abstract class ProductDto {

    private String name;
    private double price;
    private String description;
    private ProducerDto producer;

    public ProductDto(String name, double price, String description, ProducerDto producer) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProducerDto getProducer() {
        return producer;
    }

    public void setProducer(ProducerDto producer) {
        this.producer = producer;
    }
}
