package com.ecommerce.teamviewerecommerce.service;

import com.ecommerce.teamviewerecommerce.dto.OrderDto;
import com.ecommerce.teamviewerecommerce.dto.OrderRequest;
import com.ecommerce.teamviewerecommerce.dto.OrderResponse;
import com.ecommerce.teamviewerecommerce.dto.OrderUpdateRequest;

public interface OrderService {

	public OrderDto placeOrder(OrderDto orderDto);

	OrderResponse getAllOrders(int pageNo, int pageSize);

	OrderDto getOrderById(long id);

	OrderDto updateOrderById(OrderUpdateRequest orderUpdateRequest, long id);

	void deleteOrderById(long id);

}
