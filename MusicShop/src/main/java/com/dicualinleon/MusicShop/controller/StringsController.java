package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.controller.base.ProductControllerBase;
import com.dicualinleon.MusicShop.domain.ShoppingCart;
import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.domain.products.Strings;
import com.dicualinleon.MusicShop.dto.products.GuitarDto;
import com.dicualinleon.MusicShop.dto.products.StringsDto;
import com.dicualinleon.MusicShop.mapper.products.StringsMapper;
import com.dicualinleon.MusicShop.service.products.StringsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/strings")
public class StringsController extends ProductControllerBase {

    private StringsService stringsService;
    private StringsMapper stringsMapper;
    private ShoppingCart shoppingCart;

    public StringsController(StringsService stringsService,
                             StringsMapper stringsMapper,
                             ShoppingCart shoppingCart) {
        this.stringsService = stringsService;
        this.stringsMapper = stringsMapper;
        this.shoppingCart = shoppingCart;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StringsDto> get(@PathVariable("id") Long id) {
        Strings strings = stringsService.get(id);
        if(strings == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        StringsDto stringsDto = stringsMapper.toDto(strings);
        return ResponseEntity
                .ok()
                .body(stringsDto);
    }

    @PutMapping(value = "/{id}/add_to_cart")
    public ResponseEntity<String> purchase(@PathVariable("id") Long id) {

        Strings strings = stringsService.get(id);
        if(strings == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        StringsDto stringsDto = stringsMapper.toDto(strings);
        return AddToShoppingCart(shoppingCart, stringsDto);
    }
}
