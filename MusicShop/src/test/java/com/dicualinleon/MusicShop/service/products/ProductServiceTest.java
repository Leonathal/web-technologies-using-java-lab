package com.dicualinleon.MusicShop.service.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.exception.ProductNotFoundException;
import com.dicualinleon.MusicShop.repository.products.ProductDaoRepository;
import com.dicualinleon.MusicShop.service.products.ProductService;
import com.dicualinleon.MusicShop.utils.ProductTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductDaoRepository productDaoRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void getProductHappyFlow() {
        // arrange
        long id = 1;
        Product product = new Product(
                1,
                "name",
                2.0,
                "desc",
                "producer",
                ProductTypes.STRINGS,
                100
        );
        when(productDaoRepository.getProduct(id))
                .thenReturn(Optional.of(product));

        // act
        Product result = productService.get(id);

        // assert
        assertEquals(result, product);
    }

    @Test
    public void getProductProductNotFoundException() throws Exception {
        // arrange
        long id = 1;
        Product product = new Product(
                1,
                "name",
                2.0,
                "desc",
                "producer",
                ProductTypes.STRINGS,
                100
        );
        when(productDaoRepository.getProduct(id))
                .thenReturn(Optional.empty());

        // act
        ProductNotFoundException exception = assertThrows(
                ProductNotFoundException.class,
                () -> productService.get(id)
        );

        // assert
        assertTrue(exception.getMessage().contains(String.valueOf(id)));
    }
}
