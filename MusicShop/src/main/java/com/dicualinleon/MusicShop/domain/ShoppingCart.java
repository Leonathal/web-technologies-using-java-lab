package com.dicualinleon.MusicShop.domain;

import com.dicualinleon.MusicShop.dto.products.ProductDto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class ShoppingCart {

    private List<ProductDto> products = new ArrayList<>();

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public void addProduct(ProductDto product) {
        this.products.add(product);
    }
}
