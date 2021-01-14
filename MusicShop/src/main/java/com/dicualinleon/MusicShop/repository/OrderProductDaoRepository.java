package com.dicualinleon.MusicShop.repository;

import com.dicualinleon.MusicShop.domain.Order;
import com.dicualinleon.MusicShop.domain.OrderProduct;
import com.dicualinleon.MusicShop.exception.OrderProductNotCreatedException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderProductDaoRepository {

    private JdbcTemplate jdbcTemplate;

    public OrderProductDaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<OrderProduct> getOrderProductMapper() {
        RowMapper<OrderProduct> orderProductRowMapper = (resultSet, rowNum) -> {
            return OrderProduct.builder()
                    .id(resultSet.getLong("id"))
                    .orderId(resultSet.getLong("orderId"))
                    .productId(resultSet.getLong("productId"))
                    .build();
        };
        return orderProductRowMapper;
    }

    public OrderProduct create(long orderId, long productId) {
        String sql = "INSERT INTO order_product VALUES (?, ?, ?)";
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setObject(2, orderId);
            preparedStatement.setObject(3, productId);
            return preparedStatement;
        };

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int numberOfRowsAdded = jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        if(numberOfRowsAdded == 0) {
            throw new OrderProductNotCreatedException(orderId, productId);
        }

        OrderProduct orderProduct = OrderProduct.builder()
                .id(generatedKeyHolder.getKey().longValue())
                .orderId(orderId)
                .productId(productId)
                .build();
        return orderProduct;
    }

    public Optional<long[]> ProductsInOrder(long orderId) {
        String sql = "SELECT * FROM order_product op WHERE op.orderId = ?";

        RowMapper<OrderProduct> mapper = getOrderProductMapper();

        List<OrderProduct> orderProducts = jdbcTemplate.query(sql, mapper, orderId);
        if(orderProducts != null && !orderProducts.isEmpty()) {
            long[] ret = new long[orderProducts.size()];
            for(int i = 0 ; i < orderProducts.size() ; i++) {
                ret[i] = orderProducts.get(i).getProductId();
            }
            return Optional.of(ret);
        }
        else {
            return Optional.empty();
        }
    }
}
