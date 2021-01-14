package com.dicualinleon.MusicShop.mapper;

import com.dicualinleon.MusicShop.domain.Order;
import com.dicualinleon.MusicShop.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .userId(orderDto.getUserId())
                .addressId(orderDto.getAddressId())
                .status(orderDto.getStatus())
                .build();
    }

    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .addressId(order.getAddressId())
                .status(order.getStatus())
                .build();
    }
}
