package com.dicualinleon.MusicShop.repository;

import com.dicualinleon.MusicShop.domain.Address;
import com.dicualinleon.MusicShop.exception.AddressNotCreatedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class AddressDaoRepository {

    private JdbcTemplate jdbcTemplate;

    public AddressDaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Address> GetAddressMapper() {
        RowMapper<Address> addressRowMapper = (resultSet, rowNum) -> {
            return Address.builder()
                    .id(resultSet.getLong("id"))
                    .country(resultSet.getString("country"))
                    .address(resultSet.getString("address"))
                    .build();
        };
        return addressRowMapper;
    }

    public Address create(Address address) {
        String sql = "INSERT INTO address VALUES (?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, address.getCountry());
            preparedStatement.setObject(3, address.getAddress());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int numberOfAddressesCreated = jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        if(numberOfAddressesCreated == 0) {
            throw new AddressNotCreatedException(address);
        }

        Address createdAddress = Address.builder()
                .id(generatedKeyHolder.getKey().longValue())
                .country(address.getCountry())
                .address(address.getAddress())
                .build();
        return createdAddress;
    }

    public void assignAccount(Address address, long accountId) {
        String sql = "INSERT INTO account_address VALUES (?, ?, ?)";
        int numberOfRowCreated = jdbcTemplate.update(sql, null, accountId, address.getId());
    }
}
