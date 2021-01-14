package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.Order;
import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<String>> get(@PathVariable("id") long orderId) {
        List<Product> productList = orderService.get(orderId);
        if(productList == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        List<String> productNames = new ArrayList<>();
        for(int i = 0 ; i < productList.size() ; i++) {
            productNames.add(productList.get(i).getName());
        }
        return ResponseEntity
                .ok()
                .body(productNames);
    }
}
