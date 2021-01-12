package com.dicualinleon.MusicShop.service.products;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.dto.products.GuitarDto;
import com.dicualinleon.MusicShop.mapper.products.GuitarMapper;
import com.dicualinleon.MusicShop.repository.products.GuitarDaoRepository;
import org.springframework.stereotype.Service;

@Service
public class GuitarService {

    private GuitarDaoRepository guitarDaoRepository;
    private GuitarMapper guitarMapper;

    public GuitarService(GuitarDaoRepository guitarDaoRepository, GuitarMapper guitarMapper) {
        this.guitarDaoRepository = guitarDaoRepository;
        this.guitarMapper = guitarMapper;
    }

    public GuitarDto save(GuitarDto guitarDto) {
        Guitar guitar = guitarMapper.toEntity(guitarDto);
        Guitar savedGuitar = guitarDaoRepository.save(guitar);
        GuitarDto savedGuitarDto = guitarMapper.toDto(savedGuitar);
        return savedGuitarDto;
    }

    public GuitarDto get(long id) {
        Guitar guitar = guitarDaoRepository.getOne(id);
        return guitarMapper.toDto(guitar);
    }

    public boolean remove(long id) {
        Guitar guitar = guitarDaoRepository.getOne(id);
        return guitarDaoRepository.delete(guitar);
    }
}
