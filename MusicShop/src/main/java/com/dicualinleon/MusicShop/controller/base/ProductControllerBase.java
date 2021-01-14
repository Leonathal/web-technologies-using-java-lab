package com.dicualinleon.MusicShop.controller.base;

import com.dicualinleon.MusicShop.domain.ShoppingCart;
import com.dicualinleon.MusicShop.dto.products.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ProductControllerBase {

    protected ResponseEntity<String> AddToShoppingCart(ShoppingCart shoppingCart, ProductDto productDto) {
        if(productDto.getQuantity() > 0) {
            shoppingCart.addProduct(productDto);
            return ResponseEntity
                    .ok()
                    .body("Product added");
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body("Product out of stock");
    }
}
