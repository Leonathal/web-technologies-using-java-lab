package com.dicualinleon.MusicShop.repository.products;
import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.utils.GuitarTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GuitarDaoRepository {

    private JdbcTemplate jdbcTemplate;

    public GuitarDaoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Guitar> GetGuitarMapper() {
        RowMapper<Guitar> guitarRowMapper = (resultSet, rowNum) -> {
            return Guitar.builder()
                    .id(resultSet.getLong("id"))
                    .productId(resultSet.getLong("productId"))
                    .guitarType(GuitarTypes.valueOf(resultSet.getString("guitarType")))
                    .build();
        };
        return guitarRowMapper;
    }

    public Optional<Guitar> getGuitar(Long id) {
        String sql = "SELECT * FROM guitar gu WHERE gu.id = ?";

        RowMapper<Guitar> mapper = GetGuitarMapper();

        List<Guitar> guitars = jdbcTemplate.query(sql, mapper, id);
        if(guitars != null && !guitars.isEmpty()) {
            return Optional.of(guitars.get(0));
        }
        else {
            return Optional.empty();
        }
    }
}
