package com.dicualinleon.MusicShop.service.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.exception.ProductNotFoundException;
import com.dicualinleon.MusicShop.repository.products.ProductDaoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductDaoRepository productDaoRepository;

    public ProductService(ProductDaoRepository productDaoRepository) {
        this.productDaoRepository = productDaoRepository;
    }

    public Product get(long id) {
        Optional<Product> productOptional = productDaoRepository.getProduct(id);
        if(productOptional.isPresent()) {
            return productOptional.get();
        }
        else {
            throw new ProductNotFoundException(id);
        }
    }
}
