package com.dicualinleon.MusicShop.domain.products.base;

import com.dicualinleon.MusicShop.utils.ProductTypes;

public interface IProduct {

    /**
     * No parameters
     * @return product name
     */
    String getName();

    /**
     * No parameters
     * @return product price
     */
    double getPrice();

    /**
     * No parameters
     * @return product description
     */
    String getDescription();

    /**
     * The return is an enum
     * @return product type
     */
    ProductTypes getType();

    /**
     * The return is a class of type Producer
     * @return the producer of this item
     */
    String getProducer();

    int getQuantity();
}
