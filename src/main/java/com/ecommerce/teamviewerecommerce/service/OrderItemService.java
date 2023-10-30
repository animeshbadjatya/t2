package com.ecommerce.teamviewerecommerce.service;

import java.util.List;

import com.ecommerce.teamviewerecommerce.payload.OrderItemDto;
import com.ecommerce.teamviewerecommerce.payload.OrderItemResponse;
import com.ecommerce.teamviewerecommerce.payload.OrderResponse;
import com.ecommerce.teamviewerecommerce.payload.ProductResponse;

public interface OrderItemService {

	OrderItemDto createOrderItem(OrderItemDto orderItemDto);

	OrderItemResponse getAllOrderItems(int pageNo, int pageSize);

	OrderItemDto getOrderItemById(long id);

	OrderItemDto updateOrderItemById(OrderItemDto orderItemDto);

	void deleteOrderItemById(long id);
}
