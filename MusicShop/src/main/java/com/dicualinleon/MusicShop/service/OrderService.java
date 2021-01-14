package com.dicualinleon.MusicShop.service;

import com.dicualinleon.MusicShop.domain.Order;
import com.dicualinleon.MusicShop.domain.ShoppingCart;
import com.dicualinleon.MusicShop.domain.products.base.Product;
import com.dicualinleon.MusicShop.dto.products.ProductDto;
import com.dicualinleon.MusicShop.exception.OutOfStockException;
import com.dicualinleon.MusicShop.exception.ProductNotFoundException;
import com.dicualinleon.MusicShop.repository.OrderDaoRepository;
import com.dicualinleon.MusicShop.repository.OrderProductDaoRepository;
import com.dicualinleon.MusicShop.repository.products.ProductDaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderProductDaoRepository orderProductDaoRepository;
    private OrderDaoRepository orderDaoRepository;
    private ProductDaoRepository productDaoRepository;

    public OrderService(OrderProductDaoRepository orderProductDaoRepository,
                        OrderDaoRepository orderDaoRepository,
                        ShoppingCart shoppingCart,
                        ProductDaoRepository productDaoRepository) {
        this.orderProductDaoRepository = orderProductDaoRepository;
        this.orderDaoRepository = orderDaoRepository;
        this.productDaoRepository = productDaoRepository;
    }

    @Transactional
    public void order(long userId, long addressId, List<ProductDto> productList) {

        Order order = Order.builder()
            .userId(userId)
            .addressId(addressId)
            .status("Preparing")
            .build();

        Order createdOrder = orderDaoRepository.create(order);

        for(int i = 0 ; i < productList.size() ; i++) {
            long productId = productList.get(i).getId();
            Optional<Product> productOptional = productDaoRepository.getProduct(productId);
            if(productOptional == null)
                throw new ProductNotFoundException(productId);
            Product product = productOptional.get();
            if(product.getQuantity() == 0)
                throw new OutOfStockException(productId);
            orderProductDaoRepository.create(createdOrder.getId(), productId);
            productDaoRepository.updateProductQuantity(productId, product.getQuantity() - 1);
        }
    }
}
