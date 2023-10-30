package com.ecommerce.teamviewerecommerce.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ecommerce.teamviewerecommerce.exception.APIException;
import com.ecommerce.teamviewerecommerce.payload.OrderItemResponse;
import com.ecommerce.teamviewerecommerce.payload.OrderResponse;
import com.ecommerce.teamviewerecommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.teamviewerecommerce.payload.OrderItemDto;
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
		//Checking if Order_id in OrderItem exists in Order table
		Optional<Order> order = orderRepository.findById(orderItemDto.getOrderId());
		// If the Order_id of the OrderItem is not found in Orders table throw exception.
		if(order.isEmpty()){
			throw new ResourceNotFoundException("Order is not present in table Orders", "id",orderItemDto.getOrderId());
		}
		else{
			Double total = order.get().getTotalAmount();
			total += product.getUnitPrice()* orderItemDto.getQuantity();
			orderItem.setOrder(order.get());
			// Setting the new total based on the OrderItem
			//Setting the order again and saving in Order table.
			Order updateOrder = new Order();
			updateOrder.setId(order.get().getId());
			updateOrder.setTotalAmount(total);
			updateOrder.setStatus(order.get().getStatus());
			updateOrder.setCustomerId(order.get().getCustomerId());
			updateOrder.setBillingAddress(order.get().getBillingAddress());
			// Save the order
			orderRepository.save(updateOrder);

		}
		// OrderItem save to DB
		OrderItem newOrderItem = orderItemRepository.save(orderItem);

		return mapToDto(newOrderItem);
	}


	@Override
	public OrderItemResponse getAllOrderItems(int pageNo, int pageSize) {
		//Create Pageable instance
		Pageable pageable = PageRequest.of(pageNo,pageSize);

		Page<OrderItem> orderItems = orderItemRepository.findAll(pageable);

		if(orderItems.getTotalElements() == 0){
			throw new APIException("No OrderItems and Order are present currently");
		}

		// Get content from Page object
		List<OrderItem> listOfOrderItems = orderItems.getContent();

		List<OrderItemDto> content = listOfOrderItems.stream().map(orderItem -> mapToDto(orderItem)).collect(Collectors.toList());

		OrderItemResponse orderItemResponse = new OrderItemResponse();
		orderItemResponse.setContent(content);
		orderItemResponse.setPageNo(orderItems.getNumber());
		orderItemResponse.setPageSize(orderItems.getSize());
		orderItemResponse.setTotalElements(orderItems.getTotalElements());
		orderItemResponse.setTotalPages(orderItems.getTotalPages());
		orderItemResponse.setLast(orderItems.isLast());
		return orderItemResponse;
    }


	@Override
	public OrderItemDto getOrderItemById(long id) {
		OrderItemDto orderItemDto = null;
		Optional<OrderItem> orderItemObj = orderItemRepository.findById(id);
		if (orderItemObj.isEmpty()) {
			throw new ResourceNotFoundException("OrderItem ", "id", id);
		}
			OrderItem orderItem = orderItemObj.get();
			orderItemDto = mapToDto(orderItem);

		return orderItemDto;
	}

	@Override
	public OrderItemDto updateOrderItemById(OrderItemDto orderItemDto) {
		OrderItem orderItem = orderItemRepository.findById(orderItemDto.getId()).orElseThrow(() -> {
			return new ResourceNotFoundException("OrderItem ", " id ", orderItemDto.getId());
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

	private OrderItemDto mapToDto(OrderItem orderItem) {
		OrderItemDto orderItemDto = mapper.map(orderItem, OrderItemDto.class);
		return orderItemDto;
	}

	private OrderItem mapToEntity(OrderItemDto orderItemDto) {
		OrderItem orderItem = mapper.map(orderItemDto, OrderItem.class);
		return orderItem;

	}

}
