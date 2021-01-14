package com.dicualinleon.MusicShop.controller;

import com.dicualinleon.MusicShop.domain.Account;
import com.dicualinleon.MusicShop.domain.Address;
import com.dicualinleon.MusicShop.domain.ShoppingCart;
import com.dicualinleon.MusicShop.dto.AccountDto;
import com.dicualinleon.MusicShop.dto.AddressDto;
import com.dicualinleon.MusicShop.mapper.AccountMapper;
import com.dicualinleon.MusicShop.mapper.AddressMapper;
import com.dicualinleon.MusicShop.service.AccountService;
import com.dicualinleon.MusicShop.service.AddressService;
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
    private AddressService addressService;

    private AccountMapper accountMapper;
    private AddressMapper addressMapper;

    private ShoppingCart shoppingCart;

    public AccountController(
            AccountService accountService,
            AddressService addressService,
            AccountMapper accountMapper,
            AddressMapper addressMapper,
            ShoppingCart shoppingCart) {
        this.accountService = accountService;
        this.addressService = addressService;
        this.accountMapper = accountMapper;
        this.addressMapper = addressMapper;
        this.shoppingCart = shoppingCart;
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

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<AccountDto> get(
//            @PathVariable("id") Long id) {
//        Account account = accountService.get(id);
//        if(account == null) {
//            return ResponseEntity
//                    .notFound()
//                    .build();
//        }
//        AccountDto accountDto = accountMapper.toDto(account);
//        return ResponseEntity
//                .ok()
//                .body(accountDto);
//    }

    @GetMapping(value = "/login")
    public ResponseEntity<AccountDto> login(
            @RequestParam(name="username") String username,
            @RequestParam(name="password") String password) {
        Account account = accountService.get(username, password);
        if(account == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        AccountDto accountDto = accountMapper.toDto(account);
        shoppingCart.setCurrentUser(accountDto);
        return ResponseEntity
                .ok()
                .body(accountDto);
    }

    @PostMapping(value = "/{id}/add_address")
    public ResponseEntity<AddressDto> addAddress(
            @Valid
            @RequestBody AddressDto addressDto,
            @PathVariable("id") Long accountId
    ) {
        Address address = addressMapper.toEntity(addressDto);
        Address savedAddress = addressService.create(address, accountId);
        if(savedAddress == null) {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .build();
        }
        AddressDto savedAddressDto = addressMapper.toDto(savedAddress);
        return ResponseEntity
                .created(URI.create("/" + accountId + "/address/" + savedAddress.getId()))
                .body(savedAddressDto);
    }
}
