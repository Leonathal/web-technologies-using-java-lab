package com.dicualinleon.MusicShop.repository.products;

import com.dicualinleon.MusicShop.domain.products.Bass;
import com.dicualinleon.MusicShop.domain.products.Drums;
import com.dicualinleon.MusicShop.repository.DaoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class DrumsDaoRepository implements DaoRepository<Drums> {

    private Long id = 0L;
    private final HashMap<Long, Drums> drumsList = new HashMap<>();

    @Override
    public Drums save(Drums object) {
        drumsList.put(id++, object);
        return object;
    }

    @Override
    public boolean delete(Drums object) {
        Optional<Long> drumsKey = drumsList.keySet()
                .stream()
                .filter(key -> drumsList.get(key).equals(object))
                .findAny();
        if(drumsKey.isPresent()) {
            drumsList.remove(drumsKey, object);
            return true;
        }
        return false;
    }

    @Override
    public Drums getOne(Long id) {
        return drumsList.get(id);
    }

    @Override
    public List<Drums> getAll() {
        return new ArrayList<>(drumsList.values());
    }
}
