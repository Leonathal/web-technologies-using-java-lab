package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.ShoppingCart;
import com.dicualinleon.MusicShop.dto.products.ProductDto;
import com.dicualinleon.MusicShop.service.OrderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private ShoppingCart shoppingCart;
    private OrderService orderService;

    public ShoppingCartController(ShoppingCart shoppingCart,
                                  OrderService orderService) {
        this.shoppingCart = shoppingCart;
        this.orderService = orderService;
    }

    @GetMapping("/view")
    public List<ProductDto> viewShoppingCart() {
        return shoppingCart.getProducts();
    }

    @PutMapping("/order/{adr_id}")
    public void order(@PathVariable("adr_id") long adrId) {
        orderService.order(shoppingCart.getCurrentUser().getId(), adrId, shoppingCart.getProducts());
    }
}
