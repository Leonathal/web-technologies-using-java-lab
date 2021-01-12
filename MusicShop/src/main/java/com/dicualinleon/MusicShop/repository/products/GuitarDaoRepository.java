package com.dicualinleon.MusicShop.repository.products;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.repository.DaoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class GuitarDaoRepository implements DaoRepository<Guitar> {

    private Long id = 0L;
    private final HashMap<Long, Guitar> guitarList = new HashMap<>();

    @Override
    public Guitar save(Guitar object) {
        guitarList.put(id++, object);
        return object;
    }

    @Override
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

    @Override
    public Guitar getOne(Long id) {
        return guitarList.get(id);
    }

    @Override
    public List<Guitar> getAll() {
        return new ArrayList<>(guitarList.values());
    }
}
