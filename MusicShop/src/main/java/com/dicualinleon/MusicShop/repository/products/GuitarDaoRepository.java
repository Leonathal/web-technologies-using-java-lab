package com.dicualinleon.MusicShop.repository.products;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class GuitarDaoRepository {

    private Long id = 0L;
    private final HashMap<Long, Guitar> guitarList = new HashMap<>();

    public Guitar save(Guitar object) {
        guitarList.put(id++, object);
        return object;
    }

    public boolean delete(Guitar object) {
        Optional<Long> guitarKey = guitarList.keySet()
                .stream()
                .filter(key -> guitarList.get(key).equals(object))
                .findAny();
        if(guitarKey.isPresent()) {
            guitarList.remove(guitarKey, object);
            return true;
        }
        return false;
    }

    public Guitar getOne(Long id) {
        return guitarList.get(id);
    }

    public List<Guitar> getAll() {
        return new ArrayList<>(guitarList.values());
    }
}
