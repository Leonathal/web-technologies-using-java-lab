package com.dicualinleon.MusicShop.repository.products;
import com.dicualinleon.MusicShop.domain.products.Strings;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StringsDaoRepository {

    private JdbcTemplate jdbcTemplate;

    public StringsDaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Strings> GetStringsMapper() {
        RowMapper<Strings> guitarRowMapper = (resultSet, rowNum) -> {
            return Strings.builder()
                    .id(resultSet.getLong("id"))
                    .productId(resultSet.getLong("productId"))
                    .gauge(resultSet.getDouble("gauge"))
                    .build();
        };
        return guitarRowMapper;
    }

    public Optional<Strings> getStrings(Long id) {
        String sql = "SELECT * FROM strings st WHERE st.id = ?";

        RowMapper<Strings> mapper = GetStringsMapper();

        List<Strings> strings = jdbcTemplate.query(sql, mapper, id);
        if(strings != null && !strings.isEmpty()) {
            return Optional.of(strings.get(0));
        }
        else {
            return Optional.empty();
        }
    }
}
