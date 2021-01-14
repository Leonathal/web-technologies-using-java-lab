package com.dicualinleon.MusicShop.service;

import com.dicualinleon.MusicShop.domain.Account;
import com.dicualinleon.MusicShop.exception.AccountNotFoundException;
import com.dicualinleon.MusicShop.exception.DuplicateUsernameException;
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
        Optional<Account> duplicateTestAccount = accountDaoRepository.getAccount(account.getUsername());
        if(duplicateTestAccount.isPresent()) {
            throw new DuplicateUsernameException(account.getUsername());
        }
        return accountDaoRepository.create(account);
    }

    public Account get(long id) {
        Optional<Account> accountOptional = accountDaoRepository.getAccount(id);
        if(accountOptional.isPresent()) {
            return accountOptional.get();
        }
        else {
            throw new AccountNotFoundException(id);
        }
    }

    public Account get(String username,
                       String password) {
        Optional<Account> accountOptional = accountDaoRepository.getAccount(username, password);
        if(accountOptional.isPresent()) {
            return accountOptional.get();
        }
        else {
            throw new AccountNotFoundException(username, password);
        }
    }
}
