package com.dicualinleon.MusicShop.mapper;

import com.dicualinleon.MusicShop.domain.Account;
import com.dicualinleon.MusicShop.dto.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .username(accountDto.getUsername())
                .password(accountDto.getPassword())
                .email(accountDto.getEmail())
                .build();
    }

    public AccountDto toDto(Account account) {
        return AccountDto.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .build();
    }
}
