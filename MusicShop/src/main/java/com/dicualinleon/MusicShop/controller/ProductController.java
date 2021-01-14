package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.dto.products.ProductDto;
import com.dicualinleon.MusicShop.mapper.products.ProductMapper;
import com.dicualinleon.MusicShop.service.products.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable("id") long id) {
        Product product = productService.get(id);
        if(product == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        ProductDto productDto = productMapper.toDto(product);
        return ResponseEntity
                .ok()
                .body(productDto);
    }
}
