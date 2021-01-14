package com.dicualinleon.MusicShop.service;

import com.dicualinleon.MusicShop.domain.Address;
import com.dicualinleon.MusicShop.exception.AccountNotCreatedException;
import com.dicualinleon.MusicShop.exception.AddressNotCreatedException;
import com.dicualinleon.MusicShop.repository.AddressDaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressDaoRepository addressDaoRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    public void createAddressHappyFlow() {
        // arrange
        long accountId = 1;
        Address address = Address.builder()
                .country("Romania")
                .address("Bucharest")
                .build();
        Address newAddress = Address.builder()
                .id(1)
                .country("Romania")
                .address("Bucharest")
                .build();

        when(addressDaoRepository.create(address))
                .thenReturn(newAddress);
        doAnswer((i) -> {
            return null;
        }).when(addressDaoRepository).assignAccount(newAddress, accountId);

        // act
        Address addressResult = addressService.create(address, accountId);

        // assert
        assertEquals(1, newAddress.getId());
    }

    @Test
    public void createAddressNotCreated() throws Exception{
        // arrange
        long accountId = 1;
        Address address = Address.builder()
                .country("Romania")
                .address("Bucharest")
                .build();
        Address newAddress = Address.builder()
                .id(1)
                .country("Romania")
                .address("Bucharest")
                .build();

        when(addressDaoRepository.create(address))
                .thenThrow(new AddressNotCreatedException(address));

        // act
        AddressNotCreatedException exception = assertThrows(
                AddressNotCreatedException.class,
                () -> addressService.create(address, accountId),
                "Expected create() to throw AddressNotCreatedException"
        );

        // assert
        assertTrue(exception.getMessage().equalsIgnoreCase("Account could not be created"));
    }
}
