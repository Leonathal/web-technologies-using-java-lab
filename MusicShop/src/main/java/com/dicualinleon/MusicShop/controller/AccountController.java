package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.dto.AccountDto;
import com.dicualinleon.MusicShop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable("id") Long id) {
        AccountDto accountDto = accountService.get(id);
        return new ResponseEntity<>(accountDto, null == accountDto ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
