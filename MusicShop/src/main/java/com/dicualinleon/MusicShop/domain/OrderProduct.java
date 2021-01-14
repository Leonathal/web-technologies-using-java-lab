package com.dicualinleon.MusicShop.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderProduct {
    final private long id;
    final private long orderId;
    final private long productId;
}
