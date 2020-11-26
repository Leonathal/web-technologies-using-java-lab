package com.dicualinleon.MusicShop.repository;

import com.dicualinleon.MusicShop.domain.products.Pick;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class PickDaoRepository implements DaoRepository<Pick> {

    private Long id = 0L;
    private final HashMap<Long, Pick> pickList = new HashMap<>();

    @Override
    public Pick save(Pick object) {
        pickList.put(id++, object);
        return object;
    }

    @Override
    public boolean delete(Pick object) {
        Optional<Long> producerKey = pickList.keySet()
                .stream()
                .filter(key -> pickList.get(key).equals(object))
                .findAny();
        if(producerKey.isPresent()) {
            pickList.remove(producerKey, object);
            return true;
        }
        return false;
    }

    @Override
    public Pick getOne(Long id) {
        return pickList.get(id);
    }

    @Override
    public List<Pick> getAll() {
        return new ArrayList<>(pickList.values());
    }
}
