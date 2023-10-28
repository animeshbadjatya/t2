package com.ecommerce.teamviewerecommerce.service;

import com.ecommerce.teamviewerecommerce.dto.OrderDto;
import com.ecommerce.teamviewerecommerce.dto.OrderResponse;
import com.ecommerce.teamviewerecommerce.dto.ProductDto;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    OrderResponse getAllOrders(int pageNo, int pageSize);

    OrderDto getOrderById(long id);

    OrderDto updateOrderById(OrderDto orderDto, long id);

    void deleteOrderById(long id);

}
