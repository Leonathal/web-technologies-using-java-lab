package com.dicualinleon.MusicShop.repository.products;

import com.dicualinleon.MusicShop.domain.products.Piano;
import com.dicualinleon.MusicShop.repository.DaoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class PianoDaoRepository implements DaoRepository<Piano> {

    private Long id = 0L;
    private final HashMap<Long, Piano> pianoList = new HashMap<>();

    @Override
    public Piano save(Piano object) {
        pianoList.put(id++, object);
        return object;
    }

    @Override
    public boolean delete(Piano object) {
        Optional<Long> producerKey = pianoList.keySet()
                .stream()
                .filter(key -> pianoList.get(key).equals(object))
                .findAny();
        if(producerKey.isPresent()) {
            pianoList.remove(producerKey, object);
            return true;
        }
        return false;
    }

    @Override
    public Piano getOne(Long id) {
        return pianoList.get(id);
    }

    @Override
    public List<Piano> getAll() {
        return new ArrayList<>(pianoList.values());
    }
}
