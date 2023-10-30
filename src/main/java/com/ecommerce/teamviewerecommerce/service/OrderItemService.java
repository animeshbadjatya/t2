package com.ecommerce.teamviewerecommerce.service;

import java.util.List;

import com.ecommerce.teamviewerecommerce.dto.OrderItemDto;

public interface OrderItemService {

	OrderItemDto createOrderItem(OrderItemDto orderItemDto);

	List<OrderItemDto> getAllOrderItems();

	OrderItemDto getOrderItemById(long id);

	OrderItemDto updateOrderItemById(OrderItemDto orderItemDto);

	void deleteOrderItemById(long id);
}
