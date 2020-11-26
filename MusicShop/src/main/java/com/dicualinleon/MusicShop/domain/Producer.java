package com.dicualinleon.MusicShop.domain;

import com.dicualinleon.MusicShop.utils.ProductTypes;

import java.util.List;

public class Producer {

    //region Data members
    private String name;
    private String description;
    //endregion

    //region Constructors
    Producer(String name, String description){
        this.name = name;
        this.description = description;
    }
    //endregion

    //region Getters / Setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    //endregion

    //region Methods
    public List<ProductTypes> produces() {
        return null;
    }
    //endregion
}
