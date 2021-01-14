package com.dicualinleon.MusicShop.mapper.products;

import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.dto.products.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductDto productDto) {
        return new Product(productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getDescription(),
                productDto.getType(),
                productDto.getProducer(),
                productDto.getQuantity());
    }

    public ProductDto toDto(Product product) {
        return new ProductDto(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getProducer(),
                product.getType(),
                product.getQuantity());
    }
}
