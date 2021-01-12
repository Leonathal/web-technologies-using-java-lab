package com.dicualinleon.MusicShop.repository.products;

import com.dicualinleon.MusicShop.domain.products.Strings;
import com.dicualinleon.MusicShop.repository.DaoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class StringsDaoRepository implements DaoRepository<Strings> {

    private Long id = 0L;
    private final HashMap<Long, Strings> stringsList = new HashMap<>();

    @Override
    public Strings save(Strings object) {
        stringsList.put(id++, object);
        return object;
    }

    @Override
    public boolean delete(Strings object) {
        Optional<Long> stringsKey = stringsList.keySet()
                .stream()
                .filter(key -> stringsList.get(key).equals(object))
                .findAny();
        if(stringsKey.isPresent()) {
            stringsList.remove(stringsKey, object);
            return true;
        }
        return false;
    }

    @Override
    public Strings getOne(Long id) {
        return stringsList.get(id);
    }

    @Override
    public List<Strings> getAll() {
        return new ArrayList<>(stringsList.values());
    }
}
