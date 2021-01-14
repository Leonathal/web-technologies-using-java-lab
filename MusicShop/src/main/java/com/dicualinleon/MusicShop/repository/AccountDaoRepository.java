package com.dicualinleon.MusicShop.repository;

import com.dicualinleon.MusicShop.domain.Account;
import com.dicualinleon.MusicShop.exception.AccountNotCreatedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountDaoRepository {

    private JdbcTemplate jdbcTemplate;

    public AccountDaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Account> GetAccountMapper() {
        RowMapper<Account> accountRowMapper = (resultSet, rowNum) -> {
            return Account.builder()
                    .id(resultSet.getLong("id"))
                    .username(resultSet.getString("username"))
                    .password(resultSet.getString("password"))
                    .email(resultSet.getString("email"))
                    .build();
        };
        return accountRowMapper;
    }

    public Account create(Account account) {
        String sql = "INSERT INTO account VALUES (?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, account.getUsername());
            preparedStatement.setObject(3, account.getPassword());
            preparedStatement.setObject(4, account.getEmail());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int numberOfAccountsCreated = jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        if(numberOfAccountsCreated == 0) {
            throw new AccountNotCreatedException(account);
        }

        Account createdAccount = Account.builder()
                .id(generatedKeyHolder.getKey().longValue())
                .username(account.getUsername())
                .password(account.getPassword())
                .email(account.getEmail())
                .build();
        return createdAccount;
    }

    public Optional<Account> getAccount(Long id) {
        String sql = "select * from account ac where ac.id = ?";

        RowMapper<Account> mapper = GetAccountMapper();

        List<Account> accounts = jdbcTemplate.query(sql, mapper, id);
        if(accounts != null && !accounts.isEmpty()) {
            return Optional.of(accounts.get(0));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Account> getAccount(String username,
                                        String password) {
        String sql = "select * from account ac where ac.username = ? and ac.password = ?";

        RowMapper<Account> mapper = GetAccountMapper();

        List<Account> accounts = jdbcTemplate.query(sql, mapper, username, password);
        if(accounts != null && !accounts.isEmpty()) {
            return Optional.of(accounts.get(0));
        }
        else {
            return Optional.empty();
        }
    }
}
