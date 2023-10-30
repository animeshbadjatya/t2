package com.ecommerce.teamviewerecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.teamviewerecommerce.dto.OrderItemDto;
import com.ecommerce.teamviewerecommerce.service.OrderItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(
        name = "CRUD REST APIs for Orders Items"
)
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/order-items")
public class OrderItemController {

	private OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	@PostMapping
	public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
		OrderItemDto placedOrderItemDto = orderItemService.createOrderItem(orderItemDto);
		return new ResponseEntity<>(placedOrderItemDto, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<OrderItemDto>> getAllOrderItem() {
		List<OrderItemDto> orderItemDtoList = orderItemService.getAllOrderItems();
		return new ResponseEntity<>(orderItemDtoList, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable(name = "id") long id) {
		return new ResponseEntity<>(orderItemService.getOrderItemById(id), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<OrderItemDto> updateOrderById(@RequestBody OrderItemDto orderItemDto ) {
		OrderItemDto updatedOrderItemDto = orderItemService.updateOrderItemById(orderItemDto);
		return new ResponseEntity<>(updatedOrderItemDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrderById(@PathVariable(name = "id") long id) {
		orderItemService.deleteOrderItemById(id);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
}
