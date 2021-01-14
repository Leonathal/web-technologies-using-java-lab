package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.service.OrderService;
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

@WebMvcTest(controllers = AccountController.class)

public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderService orderService;

    @Test
    public void getOrderProductsHappyFlow() throws Exception {
        List<Product> productList = new ArrayList<>();
        Product product = new Product(
                1,
                "product",
                100,
                "desc",
                "producer",
                ProductTypes.GUITAR,
                100
        );
        productList.add(product);

        when(orderService.get(any()))
                .thenReturn(productList);

        MvcResult result = mockMvc.perform(get("/order/1"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).contains("product");

    }

    @Test
    public void getOrderProductsBadRequest() throws Exception {
        when(orderService.get(any()))
                .thenReturn(null);

        MvcResult result = mockMvc.perform(get("/order/1"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}
