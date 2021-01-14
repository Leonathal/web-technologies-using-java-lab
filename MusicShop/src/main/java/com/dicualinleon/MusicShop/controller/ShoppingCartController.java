package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.ShoppingCart;
import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.domain.products.Strings;
import com.dicualinleon.MusicShop.dto.AccountDto;
import com.dicualinleon.MusicShop.dto.products.ProductDto;
import com.dicualinleon.MusicShop.service.OrderService;
import com.dicualinleon.MusicShop.service.products.GuitarService;
import com.dicualinleon.MusicShop.service.products.StringsService;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private ShoppingCart shoppingCart;
    private OrderService orderService;
    private GuitarService guitarService;
    private StringsService stringsService;

    public ShoppingCartController(ShoppingCart shoppingCart,
                                  OrderService orderService,
                                  GuitarService guitarService,
                                  StringsService stringsService) {
        this.shoppingCart = shoppingCart;
        this.orderService = orderService;
        this.guitarService = guitarService;
        this.stringsService = stringsService;
    }

    @GetMapping("/view")
    public List<ProductDto> viewShoppingCart() {
        return shoppingCart.getProducts();
    }

    @PutMapping("/order/{adr_id}")
    public void order(@PathVariable("adr_id") long adrId) {
        AccountDto currentUser = shoppingCart.getCurrentUser();
        List<ProductDto> productDtoList = shoppingCart.getProducts();

        long[] productsIds = new long[productDtoList.size()];
        for(int i = 0 ; i < productDtoList.size() ; i++) {
            ProductDto productDto = productDtoList.get(i);
            long productId;
            if(productDto.getType() == ProductTypes.GUITAR) {
                Guitar guitar = guitarService.get(productDto.getId());
                productId = guitar.getProductId();
            }
            else {
                Strings strings = stringsService.get(productDto.getId());
                productId = strings.getProductId();
            }
            productsIds[i] = productId;
        }

        orderService.order(currentUser.getId(), adrId, productsIds);
    }
}
