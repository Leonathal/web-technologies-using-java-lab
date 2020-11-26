package com.dicualinleon.MusicShop.service;

import com.dicualinleon.MusicShop.domain.Account;
import com.dicualinleon.MusicShop.dto.AccountDto;
import com.dicualinleon.MusicShop.mapper.AccountMapper;
import com.dicualinleon.MusicShop.repository.AccountDaoRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountDaoRepository accountDaoRepository;
    private AccountMapper accountMapper;

    public AccountService(AccountDaoRepository accountDaoRepository, AccountMapper accountMapper) {
        this.accountDaoRepository = accountDaoRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDto save(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        Account savedAccount = accountDaoRepository.save(account);
        AccountDto savedAccountDto = accountMapper.toDto(savedAccount);
        return savedAccountDto;
    }

    public AccountDto get(long id) {
        Account account = accountDaoRepository.getOne(id);
        return accountMapper.toDto(account);
    }

    public boolean remove(long id) {
        Account account = accountDaoRepository.getOne(id);
        return accountDaoRepository.delete(account);
    }
}
