package com.dicualinleon.MusicShop.service;

import com.dicualinleon.MusicShop.domain.Address;
import com.dicualinleon.MusicShop.repository.AddressDaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    private AddressDaoRepository addressDaoRepository;

    public AddressService(AddressDaoRepository addressDaoRepository) {
        this.addressDaoRepository = addressDaoRepository;
    }

    @Transactional
    public Address create(Address address, long accountId) {
        Address savedAddress = addressDaoRepository.create(address);
        addressDaoRepository.assignAccount(savedAddress, accountId);
        return savedAddress;
    }
}
