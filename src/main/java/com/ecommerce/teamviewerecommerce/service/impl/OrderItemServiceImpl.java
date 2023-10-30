package com.ecommerce.teamviewerecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerce.teamviewerecommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ecommerce.teamviewerecommerce.dto.OrderItemDto;
import com.ecommerce.teamviewerecommerce.entity.Order;
import com.ecommerce.teamviewerecommerce.entity.OrderItem;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.exception.ResourceNotFoundException;
import com.ecommerce.teamviewerecommerce.repository.OrderItemRepository;
import com.ecommerce.teamviewerecommerce.repository.ProductRepository;
import com.ecommerce.teamviewerecommerce.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemRepository orderItemRepository;
	private ProductRepository productRepository;

	private OrderRepository orderRepository;
	private ModelMapper mapper;

	public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ProductRepository productRepository, OrderRepository orderRepository, ModelMapper mapper) {
		this.orderItemRepository = orderItemRepository;
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
		OrderItem orderItem = mapToEntity(orderItemDto);
		// Retrieve product entity by Id
		Product product = productRepository.findById(orderItemDto.getProductId())
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", orderItemDto.getProductId()));

		// Set product to OrderItem entity
		orderItem.setProduct(product);
		Optional<Order> order = orderRepository.findById(orderItemDto.getOrderId());
		if(order.isEmpty()){
			throw new ResourceNotFoundException("Order", "id ",orderItemDto.getOrderId());
		}
		else{
			Double total = order.get().getTotalAmount();
			total += product.getUnitPrice()* orderItemDto.getQuantity();
			orderItem.setOrder(order.get());
			Order updateOrder = new Order();
			updateOrder.setId(order.get().getId());
			updateOrder.setTotalAmount(total);
			updateOrder.setStatus(order.get().getStatus());
			updateOrder.setCustomerId(order.get().getCustomerId());
			updateOrder.setBillingAddress(order.get().getBillingAddress());
			//order.setTotalAmount(totalAmount);
			// Save the order
			orderRepository.save(updateOrder);

		}

		// OrderItem save to DB
		OrderItem newOrderItem = orderItemRepository.save(orderItem);

		return mapToDto(newOrderItem);
	}

	private OrderItemDto mapToDto(OrderItem orderItem) {
		OrderItemDto orderItemDto = mapper.map(orderItem, OrderItemDto.class);
		return orderItemDto;
	}

	private OrderItem mapToEntity(OrderItemDto orderItemDto) {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(orderItemDto.getId());
		orderItem.setQuantity(orderItemDto.getQuantity());
		return orderItem;

	}

	@Override
	public List<OrderItemDto> getAllOrderItems() {
		List<OrderItem> orderItemList = orderItemRepository.findAll();
		List<OrderItemDto> orderItemDtoList = new ArrayList<>();
		for (OrderItem orderItem : orderItemList) {
			OrderItemDto orderItemDto = mapToDto(orderItem);
			orderItemDtoList.add(orderItemDto);
		}

		return orderItemDtoList;
	}

	@Override
	public OrderItemDto getOrderItemById(long id) {
		OrderItemDto orderItemDto = null;
		Optional<OrderItem> orderItemObj = orderItemRepository.findById(id);
		if (orderItemObj.isPresent()) {
			OrderItem orderItem = orderItemObj.get();
			orderItemDto = mapToDto(orderItem);
		}

		return orderItemDto;
	}

	@Override
	public OrderItemDto updateOrderItemById(OrderItemDto orderItemDto) {
		OrderItem orderItem = orderItemRepository.findById(orderItemDto.getId()).orElseThrow(() -> {
			return new ResourceNotFoundException("Order", "id", orderItemDto.getId());
		});

		orderItemRepository.save(orderItem);
		return orderItemDto;
	}

	@Override
	public void deleteOrderItemById(long id) {
		OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Order", "id", id);
		});
		orderItemRepository.deleteById(id);

	}

}
