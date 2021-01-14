package com.dicualinleon.MusicShop.service.products;

import com.dicualinleon.MusicShop.domain.products.Guitar;
import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.exception.GuitarNotFoundException;
import com.dicualinleon.MusicShop.exception.ProductNotFoundException;
import com.dicualinleon.MusicShop.repository.products.GuitarDaoRepository;
import com.dicualinleon.MusicShop.repository.products.ProductDaoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuitarService {

    private GuitarDaoRepository guitarDaoRepository;
    private ProductDaoRepository productDaoRepository;

    public GuitarService(GuitarDaoRepository guitarDaoRepository,
                         ProductDaoRepository productDaoRepository) {
        this.guitarDaoRepository = guitarDaoRepository;
        this.productDaoRepository = productDaoRepository;
    }

    public Guitar get(long id) {
        Optional<Guitar> guitarOptional = guitarDaoRepository.getGuitar(id);
        if(guitarOptional.isPresent()) {
            Guitar guitar = guitarOptional.get();
            Optional<Product> productOptional = productDaoRepository.getProduct(guitar.getProductId());
            if(productOptional.isPresent()) {
                Product product = productOptional.get();
                Guitar guitarResult = Guitar.builder()
                        .id(guitar.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .quantity(product.getQuantity())
                        .productId(product.getId())
                        .guitarType(guitar.guitarType())
                        .build();
                return guitarResult;
            }
            else {
                throw new ProductNotFoundException(guitar.getProductId());
            }
        }
        else {
            throw new GuitarNotFoundException(id);
        }
    }
}
