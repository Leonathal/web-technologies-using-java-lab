package com.dicualinleon.MusicShop.repository;

import com.dicualinleon.MusicShop.domain.Order;
import com.dicualinleon.MusicShop.exception.OrderNotCreatedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoRepository {

    private JdbcTemplate jdbcTemplate;

    public OrderDaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Order> getOrderMapper() {
        RowMapper<Order> orderRowMapper = (resultSet, rowNum) -> {
            return Order.builder()
                    .id(resultSet.getLong("id"))
                    .userId(resultSet.getLong("userId"))
                    .addressId(resultSet.getLong("addressId"))
                    .status(resultSet.getString("status"))
                    .build();
        };
        return orderRowMapper;
    }

    public Order create(Order order) {
        String sql = "INSERT INTO order VALUES(?, ?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, order.getUserId());
            preparedStatement.setObject(3, order.getAddressId());
            preparedStatement.setObject(4, order.getStatus());
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int numberOfOrdersCreated = jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        if(numberOfOrdersCreated == 0) {
            throw new OrderNotCreatedException(order);
        }

        Order createdOrder = Order.builder()
                .id(generatedKeyHolder.getKey().longValue())
                .userId(order.getUserId())
                .addressId(order.getAddressId())
                .status(order.getStatus())
                .build();
        return createdOrder;
    }

    public Optional<Order> get(long id) {
        String sql = "SELECT * FROM order or WHERE or.id = ?";

        RowMapper<Order> mapper = getOrderMapper();

        List<Order> orderList = jdbcTemplate.query(sql, mapper, id);
        if(orderList != null && !orderList.isEmpty()) {
            return Optional.of(orderList.get(0));
        }
        else {
            return Optional.empty();
        }
    }
}
