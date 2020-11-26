package com.dicualinleon.MusicShop.repository;

import com.dicualinleon.MusicShop.domain.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class AccountDaoRepository implements DaoRepository<Account> {

    private Long id = 0L;
    private final HashMap<Long, Account> accountList = new HashMap<>();

    @Override
    public Account save(Account object) {
        accountList.put(id++, object);
        return object;
    }

    @Override
    public boolean delete(Account object) {
        Optional<Long> accountKey = accountList.keySet()
                .stream()
                .filter(key -> accountList.get(key).equals(object))
                .findAny();
        if(accountKey.isPresent()) {
            accountList.remove(accountKey, object);
            return true;
        }
        return false;
    }

    @Override
    public Account getOne(Long id) {
        return accountList.get(id);
    }

    @Override
    public List<Account> getAll() {
        return new ArrayList<>(accountList.values());
    }
}
