package com.dicualinleon.MusicShop.repository;

import com.dicualinleon.MusicShop.domain.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDaoRepository {

    private JdbcTemplate jdbcTemplate;

    public AccountDaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account create(Account account) {
        String sql = "INSERT INTO account VALUES (?, ?, ?, ?)";
        int retCode = jdbcTemplate.update(sql, null, account.getUsername(), account.getPassword(), account.getEmail());

        Account createdAccount = Account.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .build();

        return createdAccount;
    }

    public Optional<Account> getAccount(Long id) {
        String sql = "select * from account ac where ac.id = ?";

        RowMapper<Account> mapper = (resultSet, rowNum) -> {
            return Account.builder()
                    .id(resultSet.getLong("id"))
                    .username(resultSet.getString("username"))
                    .password(resultSet.getString("password"))
                    .email(resultSet.getString("email"))
                    .build();
        };

        List<Account> accounts = jdbcTemplate.query(sql, mapper, id);
        if(accounts != null && !accounts.isEmpty()) {
            return Optional.of(accounts.get(0));
        }
        else {
            return Optional.empty();
        }
    }
}
