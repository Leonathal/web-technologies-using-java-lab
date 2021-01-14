package com.dicualinleon.MusicShop.domain;

import com.dicualinleon.MusicShop.dto.AccountDto;
import com.dicualinleon.MusicShop.dto.products.ProductDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class ShoppingCart {

    private AccountDto currentUser;

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

    public void setCurrentUser(AccountDto accountDto) {
        this.currentUser = accountDto;
    }

    public AccountDto getCurrentUser() {
        return this.currentUser;
    }
}
