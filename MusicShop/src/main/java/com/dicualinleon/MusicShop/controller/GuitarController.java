package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.dto.products.GuitarDto;
import com.dicualinleon.MusicShop.service.products.GuitarService;
import com.dicualinleon.MusicShop.utils.GuitarTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/guitar")
public class GuitarController {

    private GuitarService guitarService;

    public GuitarController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuitarDto> create(@RequestBody GuitarDto guitarDto) {
        GuitarDto savedGuitarDto = guitarService.save(guitarDto);
        //return new ResponseEntity<>(savedGuitarDto, null == savedGuitarDto ? HttpStatus.EXPECTATION_FAILED : HttpStatus.CREATED);
        return ResponseEntity
                .created(null)
                .body(savedGuitarDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GuitarDto> get(@PathVariable("id") Long id) {
        GuitarDto guitarDto = guitarService.get(id);
        //return new ResponseEntity<>(guitarDto, null == guitarDto ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        return ResponseEntity
                .ok()
                .body(guitarDto);
    }
}
