package com.dicualinleon.MusicShop.mapper;

import com.dicualinleon.MusicShop.domain.Address;
import com.dicualinleon.MusicShop.dto.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(AddressDto addressDto) {
        return Address.builder()
                .id(addressDto.getId())
                .country(addressDto.getCountry())
                .address(addressDto.getAddress())
                .build();
    }

    public AddressDto toDto(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .country(address.getCountry())
                .address(address.getAddress())
                .build();
    }
}
