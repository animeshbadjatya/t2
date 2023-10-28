package com.ecommerce.teamviewerecommerce.service;

import com.ecommerce.teamviewerecommerce.dto.OrderItemDto;

public interface OrderItemService {

    OrderItemDto createOrderItem(Long productId, OrderItemDto orderItemDto);
}
