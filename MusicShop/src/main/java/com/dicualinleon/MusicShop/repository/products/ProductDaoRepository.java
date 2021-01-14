package com.dicualinleon.MusicShop.repository.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.exception.ProductNotFoundException;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDaoRepository {

    private JdbcTemplate jdbcTemplate;

    public ProductDaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Product> GetProductRowMapper() {
        RowMapper<Product> productRowMapper = (resultSet, rowNum) -> {
            return new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getString("description"),
                    resultSet.getString("producer"),
                    ProductTypes.valueOf(resultSet.getString("type")),
                    resultSet.getInt("quantity")
            );
        };
        return productRowMapper;
    }

    public Optional<Product> getProduct(Long id) {
        String sql = "SELECT * FROM product pr WHERE pr.id = ?";

        RowMapper<Product> mapper = GetProductRowMapper();

        List<Product> products = jdbcTemplate.query(sql, mapper, id);
        if(products != null && !products.isEmpty()) {
            return Optional.of(products.get(0));
        }
        else {
            return Optional.empty();
        }
    }

    public void updateProductQuantity(long id, int quantity) {
        String sql = "UPDATE product pr SET pr.quantity = ? WHERE pr.id = ?";
        int numberOfUpdatedProducts = jdbcTemplate.update(sql, quantity, id);
        if(numberOfUpdatedProducts == 0) {
            throw new ProductNotFoundException(id);
        }
    }
}
