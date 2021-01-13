package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.Account;
import com.dicualinleon.MusicShop.dto.AccountDto;
import com.dicualinleon.MusicShop.mapper.AccountMapper;
import com.dicualinleon.MusicShop.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> create(
            @Valid
            @RequestBody AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        Account createdAccount = accountService.create(account);
        if(createdAccount == null) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .build();
        }
        AccountDto createdAccountDto = accountMapper.toDto(createdAccount);
        return ResponseEntity
                .created(URI.create("/account/" + createdAccount.getId()))
                .body(createdAccountDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> get(
            @PathVariable("id") Long id) {
        Account account = accountService.get(id);
        if(account == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        AccountDto accountDto = accountMapper.toDto(account);
        return ResponseEntity
                .ok()
                .body(accountDto);
    }
}
