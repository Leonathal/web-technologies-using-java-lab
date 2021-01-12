package com.dicualinleon.MusicShop.repository.products;

import com.dicualinleon.MusicShop.domain.products.Bass;
import com.dicualinleon.MusicShop.repository.DaoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class BassDaoRepository implements DaoRepository<Bass> {

    private Long id = 0L;
    private final HashMap<Long, Bass> bassList = new HashMap<>();

    @Override
    public Bass save(Bass object) {
        bassList.put(id++, object);
        return object;
    }

    @Override
    public boolean delete(Bass object) {
        Optional<Long> bassKey = bassList.keySet()
                .stream()
                .filter(key -> bassList.get(key).equals(object))
                .findAny();
        if(bassKey.isPresent()) {
            bassList.remove(bassKey, object);
            return true;
        }
        return false;
    }

    @Override
    public Bass getOne(Long id) {
        return bassList.get(id);
    }

    @Override
    public List<Bass> getAll() {
        return new ArrayList<>(bassList.values());
    }
}
