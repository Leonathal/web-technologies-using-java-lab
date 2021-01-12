package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.dto.AccountDto;
import com.dicualinleon.MusicShop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> create(@RequestBody AccountDto accountDto) {
        AccountDto savedAccountDto = accountService.save(accountDto);
        return new ResponseEntity<>(savedAccountDto, null == savedAccountDto ? HttpStatus.EXPECTATION_FAILED : HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> get(@PathVariable("id") Long id) {
        AccountDto accountDto = accountService.get(id);
        return new ResponseEntity<>(accountDto, null == accountDto ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
