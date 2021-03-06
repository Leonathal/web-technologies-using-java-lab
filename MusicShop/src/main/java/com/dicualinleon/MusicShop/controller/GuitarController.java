package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.controller.base.ProductControllerBase;
import com.dicualinleon.MusicShop.domain.ShoppingCart;
import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.dto.products.GuitarDto;
import com.dicualinleon.MusicShop.mapper.products.GuitarMapper;
import com.dicualinleon.MusicShop.service.products.GuitarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/guitar")
public class GuitarController extends ProductControllerBase {

    private GuitarService guitarService;
    private GuitarMapper guitarMapper;
    private ShoppingCart shoppingCart;

    public GuitarController(GuitarService guitarService,
                            GuitarMapper guitarMapper,
                            ShoppingCart shoppingCart) {
        this.guitarService = guitarService;
        this.guitarMapper = guitarMapper;
        this.shoppingCart = shoppingCart;
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<GuitarDto> create(
//            @Valid
//            @RequestBody GuitarDto guitarDto) {
//        GuitarDto savedGuitarDto = guitarService.save(guitarDto);
//        //return new ResponseEntity<>(savedGuitarDto, null == savedGuitarDto ? HttpStatus.EXPECTATION_FAILED : HttpStatus.CREATED);
//        return ResponseEntity
//                .created(null)
//                .body(savedGuitarDto);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GuitarDto> get(@PathVariable("id") Long id) {
        Guitar guitar = guitarService.get(id);
        if(guitar == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        GuitarDto guitarDto = guitarMapper.toDto(guitar);
        return ResponseEntity
                .ok()
                .body(guitarDto);
    }

    @PutMapping(value = "/{id}/add_to_cart")
    public ResponseEntity<String> purchase(@PathVariable("id") Long id) {

        Guitar guitar = guitarService.get(id);
        if(guitar == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        GuitarDto guitarDto = guitarMapper.toDto(guitar);
        return AddToShoppingCart(shoppingCart, guitarDto);
    }
}
