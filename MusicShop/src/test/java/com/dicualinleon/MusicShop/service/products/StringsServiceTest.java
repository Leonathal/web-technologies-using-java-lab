package com.dicualinleon.MusicShop.service.products;

import com.dicualinleon.MusicShop.domain.products.Strings;
import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.exception.ProductNotFoundException;
import com.dicualinleon.MusicShop.exception.StringsNotFoundException;
import com.dicualinleon.MusicShop.repository.products.ProductDaoRepository;
import com.dicualinleon.MusicShop.repository.products.StringsDaoRepository;
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
public class StringsServiceTest {

    @Mock
    private StringsDaoRepository stringsDaoRepository;

    @Mock
    private ProductDaoRepository productDaoRepository;

    @InjectMocks
    private StringsService stringsService;

    @Test
    public void getStringsHappyFlow() {
        // arrange
        long productId = 2;
        long id = 1;
        String name = "Guitar";
        double price = 2.0;
        String description = "desc";
        String producer = "producer";
        int quantity = 100;
        double gauge = 0.10;

        Strings simpleStrings = Strings.builder()
                .id(id)
                .gauge(gauge)
                .productId(productId)
                .build();

        Strings savedStrings = Strings.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .producer(producer)
                .productId(productId)
                .gauge(gauge)
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

        when(stringsDaoRepository.getStrings(id))
                .thenReturn(Optional.of(simpleStrings));
        when(productDaoRepository.getProduct(productId))
                .thenReturn(Optional.of(savedProduct));

        // act
        Strings result = stringsService.get(id);

        // assert
        assertEquals(savedStrings.getId(), result.getId());
        assertEquals(savedStrings.getName(), result.getName());
        assertEquals(savedStrings.getPrice(), result.getPrice());
        assertEquals(savedStrings.getDescription(), result.getDescription());
        assertEquals(savedStrings.getType(), result.getType());
        assertEquals(savedStrings.getProducer(), result.getProducer());
        assertEquals(savedStrings.getProductId(), result.getProductId());
        assertEquals(savedStrings.getGauge(), result.getGauge());
        assertEquals(savedStrings.getQuantity(), result.getQuantity());
    }

    @Test
    public void getStringsStringsNotFoundException() throws Exception {
// arrange
        long productId = 2;
        long id = 1;
        String name = "Guitar";
        double price = 2.0;
        String description = "desc";
        String producer = "producer";
        int quantity = 100;
        double gauge = 0.10;

        Strings simpleStrings = Strings.builder()
                .id(id)
                .gauge(gauge)
                .productId(productId)
                .build();

        Strings savedStrings = Strings.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .producer(producer)
                .productId(productId)
                .gauge(gauge)
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

        when(stringsDaoRepository.getStrings(id))
                .thenReturn(Optional.empty());
//        when(productDaoRepository.getProduct(productId))
//                .thenReturn(Optional.of(savedProduct));

        // act
        StringsNotFoundException exception = assertThrows(
                StringsNotFoundException.class,
                () -> stringsService.get(id)
        );

        // assert
        assertTrue(exception.getMessage().contains(String.valueOf(id)));
    }

    @Test
    public void getStringsProductNotFoundException() throws Exception {
        // arrange
        long productId = 2;
        long id = 1;
        String name = "Guitar";
        double price = 2.0;
        String description = "desc";
        String producer = "producer";
        int quantity = 100;
        double gauge = 0.10;

        Strings simpleStrings = Strings.builder()
                .id(id)
                .gauge(gauge)
                .productId(productId)
                .build();

        Strings savedStrings = Strings.builder()
                .id(id)
                .name(name)
                .price(price)
                .description(description)
                .producer(producer)
                .productId(productId)
                .gauge(gauge)
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

        when(stringsDaoRepository.getStrings(id))
                .thenReturn(Optional.of(simpleStrings));
        when(productDaoRepository.getProduct(productId))
                .thenReturn(Optional.empty());

        // act
        ProductNotFoundException exception = assertThrows(
                ProductNotFoundException.class,
                () -> stringsService.get(id)
        );

        // assert
        assertTrue(exception.getMessage().contains(String.valueOf(productId)));
    }
}
