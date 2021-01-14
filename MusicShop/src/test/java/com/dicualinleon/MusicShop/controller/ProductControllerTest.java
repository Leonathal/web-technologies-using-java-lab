package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.dto.products.ProductDto;
import com.dicualinleon.MusicShop.mapper.products.ProductMapper;
import com.dicualinleon.MusicShop.service.products.ProductService;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;
    @MockBean
    private ProductMapper productMapper;

    @Test
    public void getProductHappyFlow() throws Exception {
        long productId = 1;
        Product product = new Product(
                1,
                "product",
                100,
                "desc",
                "producer",
                ProductTypes.GUITAR,
                100
        );
        ProductDto productDto = new ProductDto(
                1,
                "product",
                100,
                "desc",
                "producer",
                ProductTypes.GUITAR,
                100);

        when(productService.get(productId))
                .thenReturn(product);
        when(productMapper.toDto(product))
                .thenReturn(productDto);

        mockMvc.perform(
                get("/product/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
