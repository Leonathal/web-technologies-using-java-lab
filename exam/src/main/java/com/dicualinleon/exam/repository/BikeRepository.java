package com.dicualinleon.exam.repository;

import com.dicualinleon.exam.model.Bike;
import com.dicualinleon.exam.utils.BikeType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class BikeRepository {

    private JdbcTemplate jdbcTemplate;

    public BikeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Bike createBike(Bike bike) {
        String sql = "INSERT INTO bikes VALUES(null, ?, ?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bike.getBrand());
            preparedStatement.setString(2, bike.getModel());
            preparedStatement.setDouble(3, bike.getPrice());
            preparedStatement.setString(4, bike.getColor());
            preparedStatement.setInt(5, bike.getType().getValue());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);

        bike.setId(generatedKeyHolder.getKey().longValue());
        return bike;
    }

    private RowMapper<Bike> getRowMapper() {
        RowMapper<Bike> rowMapper = (resultSet, rowNo) -> Bike.builder()
                .id(resultSet.getLong("id"))
                .brand(resultSet.getString("brand"))
                .model(resultSet.getString("model"))
                .price(resultSet.getDouble("price"))
                .color(resultSet.getString("color"))
                .type(BikeType.fromInt(resultSet.getInt("type")))
                .build();

        return rowMapper;
    }

    public List<Bike> getAll(BikeType type, String brandName) {
        RowMapper<Bike> rowMapper = getRowMapper();
        String sql;

        if(type == null && brandName == null) {
            sql = "SELECT * FROM bikes";
            return jdbcTemplate.query(sql, rowMapper);
        }
        else if(type == null && brandName != null) {
            sql = "SELECT * FROM bikes WHERE bikes.brand = ?";
            return jdbcTemplate.query(sql, rowMapper, brandName);
        }
        else if(type != null && brandName == null) {
            sql = "SELECT * FROM bikes WHERE bikes.type = ?";
            return jdbcTemplate.query(sql, rowMapper, type.getValue());
        }
        else {
            sql = "SELECT * FROM bikes WHERE bikes.brand = ? AND bikes.type = ?";
            return jdbcTemplate.query(sql, rowMapper, brandName, type.getValue());
        }
    }
}
