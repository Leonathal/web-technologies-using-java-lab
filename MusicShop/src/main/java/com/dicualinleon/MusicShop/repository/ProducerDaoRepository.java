package com.dicualinleon.MusicShop.repository;

import com.dicualinleon.MusicShop.domain.Producer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class ProducerDaoRepository implements DaoRepository<Producer> {

    private Long id = 0L;
    private final HashMap<Long, Producer> producerList = new HashMap<>();

    @Override
    public Producer save(Producer object) {
        producerList.put(id++, object);
        return object;
    }

    @Override
    public boolean delete(Producer object) {
        Optional<Long> producerKey = producerList.keySet()
                .stream()
                .filter(key -> producerList.get(key).equals(object))
                .findAny();
        if(producerKey.isPresent()) {
            producerList.remove(producerKey, object);
            return true;
        }
        return false;
    }

    @Override
    public Producer getOne(Long id) {
        return producerList.get(id);
    }

    @Override
    public List<Producer> getAll() {
        return new ArrayList<>(producerList.values());
    }
}
