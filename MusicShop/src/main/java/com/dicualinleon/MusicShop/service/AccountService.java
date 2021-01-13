package com.dicualinleon.MusicShop.service;

import com.dicualinleon.MusicShop.domain.Account;
import com.dicualinleon.MusicShop.repository.AccountDaoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountDaoRepository accountDaoRepository;

    public AccountService(AccountDaoRepository accountDaoRepository) {
        this.accountDaoRepository = accountDaoRepository;
    }

    public Account create(Account account) {
        Account createdAccount = accountDaoRepository.create(account);
        return createdAccount;
    }

    public Account get(long id) {
        Optional<Account> accountOptional = accountDaoRepository.getAccount(id);
        if(accountOptional.isPresent()) {
            return accountOptional.get();
        }
        else {
            //throw new
            return null;
        }
    }
}
