package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.ShoppingCart;
import com.dicualinleon.MusicShop.dto.products.ProductDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private ShoppingCart shoppingCart;

    public ShoppingCartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @PostMapping
    public String addProduct(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "";
        }

        shoppingCart.addProduct(productDto);

        return "";
    }

    @GetMapping("/view")
    public List<ProductDto> viewShoppingCart() {
        return shoppingCart.getProducts();
    }
}
