package com.dicualinleon.MusicShop.service.products;

import com.dicualinleon.MusicShop.domain.products.Strings;
import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.exception.ProductNotFoundException;
import com.dicualinleon.MusicShop.exception.StringsNotFoundException;
import com.dicualinleon.MusicShop.repository.products.ProductDaoRepository;
import com.dicualinleon.MusicShop.repository.products.StringsDaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StringsService {

    private StringsDaoRepository stringsDaoRepository;
    private ProductDaoRepository productDaoRepository;

    public StringsService(StringsDaoRepository stringsDaoRepository,
                          ProductDaoRepository productDaoRepository) {
        this.stringsDaoRepository = stringsDaoRepository;
        this.productDaoRepository = productDaoRepository;
    }

    @Transactional
    public Strings get(long id) {
        Optional<Strings> stringsOptional = stringsDaoRepository.getStrings(id);
        if(stringsOptional.isPresent()) {
            Strings strings = stringsOptional.get();
            Optional<Product> productOptional = productDaoRepository.getProduct(strings.getProductId());
            if(productOptional.isPresent()) {
                Product product = productOptional.get();
                Strings stringsResult = Strings.builder()
                        .id(strings.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .quantity(product.getQuantity())
                        .productId(product.getId())
                        .gauge(strings.getGauge())
                        .build();
                return stringsResult;
            }
            else {
                throw new ProductNotFoundException(strings.getProductId());
            }
        }
        else {
            throw new StringsNotFoundException(id);
        }
    }
}
