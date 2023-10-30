package com.ecommerce.teamviewerecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.teamviewerecommerce.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.teamviewerecommerce.entity.Order;
import com.ecommerce.teamviewerecommerce.entity.OrderItem;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.exception.APIException;
import com.ecommerce.teamviewerecommerce.exception.ResourceNotFoundException;
import com.ecommerce.teamviewerecommerce.repository.OrderItemRepository;
import com.ecommerce.teamviewerecommerce.repository.OrderRepository;
import com.ecommerce.teamviewerecommerce.repository.ProductRepository;
import com.ecommerce.teamviewerecommerce.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public OrderDto placeOrder(OrderDto orderDto) {
		OrderResponse orderResponse = new OrderResponse();
		int totalAmount = 0;
		int totalQuantity = 0;

		for (OrderItemDto orderItem : orderDto.getOrderItems()) {
			Product product = productRepository.findById(orderItem.getProductId())
					.orElseThrow(() -> new ResourceNotFoundException("Product", "id", orderItem.getProductId()));
			if (product.getUnitsInStock() < orderItem.getQuantity()) {
				throw new APIException("requested quantity of + " + product.getName() + "+is not available in stock");
			}
			totalAmount += product.getUnitPrice();
			totalQuantity += orderItem.getQuantity();
		}

		// Create an order
		Order order = new Order();
		order.setId(orderDto.getId());
		order.setTotalAmount(totalAmount);
		order.setStatus("placed");
		order.setCustomerId(orderDto.getCustomerId());
		order.setBillingAddress(orderDto.getBillingAddress());
		//order.setTotalAmount(totalAmount);
		// Save the order
		order = orderRepository.save(order);

		for (OrderItemDto orderItem : orderDto.getOrderItems()) {
			Product product = productRepository.findById(orderItem.getProductId())
					.orElseThrow(() -> new ResourceNotFoundException("Product", "id", orderItem.getProductId()));

			// Create an order product
			OrderItem saveorderItem = new OrderItem();
			saveorderItem.setProduct(product);
			saveorderItem.setOrder(order);
			saveorderItem.setQuantity(orderItem.getQuantity());

			orderItemRepository.save(saveorderItem);
		}
		return orderDto;
	}
	@Override
	public OrderResponse getAllOrders(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Order> orders = orderRepository.findAll(pageable);

		OrderResponse orderResponse = getOrderResponse(orders);

		return orderResponse;
	}

	private OrderResponse getOrderResponse(Page<Order> orders) {
		// Get content from Page object
		List<Order> listOfProducts = orders.getContent();

		List<OrderDto> content = listOfProducts.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());

		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setContent(content);
		orderResponse.setPageNo(orders.getNumber());
		orderResponse.setPageSize(orders.getSize());
		orderResponse.setTotalElements(orders.getTotalElements());
		orderResponse.setTotalPages(orders.getTotalPages());
		orderResponse.setLast(orders.isLast());
		return orderResponse;
	}

	private OrderDto mapToDTO(Order order) {
		OrderDto orderDto = mapper.map(order, OrderDto.class);
		OrderDto orderResponse = new OrderDto();
		orderResponse.setId(order.getId());
		orderResponse.setCustomerId(order.getCustomerId());
		orderResponse.setTotalAmount(order.getTotalAmount());
		orderResponse.setBillingAddress(order.getBillingAddress());
		orderResponse.setStatus(order.getStatus());
		orderResponse.setDateCreated(order.getDateCreated());
		orderResponse.setLastUpdated(order.getLastUpdated());
		return orderDto;
	}

	@Override
	public OrderDto getOrderById(long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Order", "id", id);
		});
		return mapToDTO(order);
	}

	@Override
	@Transactional
	public OrderDto updateOrderById(OrderUpdateRequest orderUpdateRequest, long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Order", "id", id);
		});

		if (orderUpdateRequest.getBillingAddress() != null)
			order.setBillingAddress(orderUpdateRequest.getBillingAddress());

		Order updatedOrder = orderRepository.save(order);

		// updating the stock if order is cancelled
		if (orderUpdateRequest.getStatus() != null && orderUpdateRequest.getStatus().equalsIgnoreCase("cancel")) {
			order.setStatus(orderUpdateRequest.getStatus());

			List<OrderItem> orderItemsList = updatedOrder.getOrderItems();
			for (OrderItem orderItem : orderItemsList) {
				Product product = orderItem.getProduct();
				product.setUnitsInStock(product.getUnitsInStock() + orderItem.getQuantity());
				productRepository.save(product);
			}
		}
		return mapToDTO(updatedOrder);
	}

	@Override
	@Transactional
	public void deleteOrderById(long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Order", "id", id);
		});

		List<OrderItem> orderItemsList = order.getOrderItems();
		for (OrderItem orderItem : orderItemsList) {
			Product product = orderItem.getProduct();
			product.setUnitsInStock(product.getUnitsInStock() + orderItem.getQuantity());
			productRepository.save(product);

			orderItemRepository.deleteById(orderItem.getId());
		}
		orderRepository.delete(order);
	}

}
