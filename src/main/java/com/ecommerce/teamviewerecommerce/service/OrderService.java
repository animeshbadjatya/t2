package com.ecommerce.teamviewerecommerce.service;

import com.ecommerce.teamviewerecommerce.payload.OrderDto;
import com.ecommerce.teamviewerecommerce.payload.OrderResponse;
import com.ecommerce.teamviewerecommerce.payload.OrderUpdateRequest;

public interface OrderService {

	public OrderDto placeOrder(OrderDto orderDto);

	OrderResponse getAllOrders(int pageNo, int pageSize);

	OrderDto getOrderById(long id);

	OrderDto updateOrderById(OrderUpdateRequest orderUpdateRequest, long id);

	void deleteOrderById(long id);

}
