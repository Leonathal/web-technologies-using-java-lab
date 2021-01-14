package com.dicualinleon.MusicShop.service.products;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.exception.GuitarNotFoundException;
import com.dicualinleon.MusicShop.exception.ProductNotFoundException;
import com.dicualinleon.MusicShop.repository.products.GuitarDaoRepository;
import com.dicualinleon.MusicShop.repository.products.ProductDaoRepository;
import com.dicualinleon.MusicShop.service.products.GuitarService;
import com.dicualinleon.MusicShop.utils.GuitarTypes;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuitarServiceTest {

    @Mock
    private GuitarDaoRepository guitarDaoRepository;

    @Mock
    private ProductDaoRepository productDaoRepository;

    @InjectMocks
    private GuitarService guitarService;

    @Test
    public void getGuitarHappyFlow() {
        // arrange
        long productId = 2;
        long id = 1;
        String name = "Guitar";
        double price = 2.0;
        String description = "desc";
        String producer = "producer";
        int quantity = 100;
        GuitarTypes guitarType = GuitarTypes.ELECTRIC;

        Guitar simpleGuitar = Guitar.builder()
                .id(id)
                .guitarType(guitarType)
                .productId(productId)
                .build();

        Guitar savedGuitar = Guitar.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .producer(producer)
                .productId(productId)
                .guitarType(guitarType)
                .quantity(quantity)
                .build();

        Product savedProduct = new Product(
                productId,
                name,
                price,
                description,
                producer,
                ProductTypes.GUITAR,
                quantity
        );

        when(guitarDaoRepository.getGuitar(id))
                .thenReturn(Optional.of(simpleGuitar));
        when(productDaoRepository.getProduct(productId))
                .thenReturn(Optional.of(savedProduct));

        // act
        Guitar result = guitarService.get(id);

        // assert
        assertEquals(savedGuitar.getId(), result.getId());
        assertEquals(savedGuitar.getName(), result.getName());
        assertEquals(savedGuitar.getPrice(), result.getPrice());
        assertEquals(savedGuitar.getDescription(), result.getDescription());
        assertEquals(savedGuitar.getType(), result.getType());
        assertEquals(savedGuitar.getProducer(), result.getProducer());
        assertEquals(savedGuitar.getProductId(), result.getProductId());
        assertEquals(savedGuitar.guitarType(), result.guitarType());
        assertEquals(savedGuitar.getQuantity(), result.getQuantity());
    }

    @Test
    public void getGuitarGuitarNotFoundException() throws Exception {
// arrange
        long productId = 2;
        long id = 1;
        String name = "Guitar";
        double price = 2.0;
        String description = "desc";
        String producer = "producer";
        int quantity = 100;
        GuitarTypes guitarType = GuitarTypes.ELECTRIC;

        Guitar simpleGuitar = Guitar.builder()
                .id(id)
                .guitarType(guitarType)
                .productId(productId)
                .build();

        Guitar savedGuitar = Guitar.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .producer(producer)
                .productId(productId)
                .guitarType(guitarType)
                .quantity(quantity)
                .build();

        Product savedProduct = new Product(
                productId,
                name,
                price,
                description,
                producer,
                ProductTypes.GUITAR,
                quantity
        );

        when(guitarDaoRepository.getGuitar(id))
                .thenReturn(Optional.empty());
//        when(productDaoRepository.getProduct(productId))
//                .thenReturn(Optional.of(savedProduct));

        // act
        GuitarNotFoundException exception = assertThrows(
                GuitarNotFoundException.class,
                () -> guitarService.get(id)
        );

        // assert
        assertTrue(exception.getMessage().contains(String.valueOf(id)));
    }

    @Test
    public void getGuitarProductNotFoundException() throws Exception {
        // arrange
        long productId = 2;
        long id = 1;
        String name = "Guitar";
        double price = 2.0;
        String description = "desc";
        String producer = "producer";
        int quantity = 100;
        GuitarTypes guitarType = GuitarTypes.ELECTRIC;

        Guitar simpleGuitar = Guitar.builder()
                .id(id)
                .guitarType(guitarType)
                .productId(productId)
                .build();

        Guitar savedGuitar = Guitar.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .producer(producer)
                .productId(productId)
                .guitarType(guitarType)
                .quantity(quantity)
                .build();

        Product savedProduct = new Product(
                productId,
                name,
                price,
                description,
                producer,
                ProductTypes.GUITAR,
                quantity
        );

        when(guitarDaoRepository.getGuitar(id))
                .thenReturn(Optional.of(simpleGuitar));
        when(productDaoRepository.getProduct(productId))
                .thenReturn(Optional.empty());

        // act
        ProductNotFoundException exception = assertThrows(
                ProductNotFoundException.class,
                () -> guitarService.get(id)
        );

        // assert
        assertTrue(exception.getMessage().contains(String.valueOf(productId)));
    }
}
