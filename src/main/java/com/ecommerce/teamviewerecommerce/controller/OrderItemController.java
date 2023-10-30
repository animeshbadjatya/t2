package com.ecommerce.teamviewerecommerce.controller;

import java.util.List;

import com.ecommerce.teamviewerecommerce.payload.OrderItemResponse;
import com.ecommerce.teamviewerecommerce.utils.AppConstants;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.teamviewerecommerce.payload.OrderItemDto;
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

	@Operation(
			summary = "Adds Item in Order REST API",
			description = "This API add the item in the Order table",
			responses = {
					@ApiResponse(
							responseCode = "201",
							description = "Http Status 201 created."
					)
			}
	)
	@PostMapping
	public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
		OrderItemDto placedOrderItemDto = orderItemService.createOrderItem(orderItemDto);
		return new ResponseEntity<>(placedOrderItemDto, HttpStatus.CREATED);
	}

	@Operation(
			summary = "Get the list of all the items for all the Orders",
			description = "This API get the all the items for all the Orders with Pagination",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "This API returns all the Products with Page Number starting from 0 and having Default Page Size of 10"
					)
			}
	)
	@GetMapping
	public ResponseEntity<OrderItemResponse> getAllOrderItem(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
	) {
		OrderItemResponse orderItemDtoList = orderItemService.getAllOrderItems(pageNo, pageSize);
		return new ResponseEntity<>(orderItemDtoList, HttpStatus.CREATED);
	}

	@Operation(
			summary = "Get the item with the specified ID",
			description = "This API get the item with specified ID",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "This API returns the Products with the specified ID"
					)
			}
	)
	@GetMapping("/{id}")
	public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable(name = "id") long id) {
		return new ResponseEntity<>(orderItemService.getOrderItemById(id), HttpStatus.OK);
	}

	@Operation(
			summary = "Update the Item with the specified ID",
			description = "This API updates the item with specified ID",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "This API returns the updated item with the specified ID"
					)
			}
	)
	@PutMapping
	public ResponseEntity<OrderItemDto> updateOrderItemById(@RequestBody OrderItemDto orderItemDto ) {
		OrderItemDto updatedOrderItemDto = orderItemService.updateOrderItemById(orderItemDto);
		return new ResponseEntity<>(updatedOrderItemDto, HttpStatus.OK);
	}

	@Operation(
			summary = "Deletes the Item with the specified ID",
			description = "This API deletes the Item with specified ID",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "This API returns Success if the item was deleted"
					),
					@ApiResponse(
							responseCode = "404",
							description = "This API failed to find the item to delete"
					)
			}
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrderItemById(@PathVariable(name = "id") long id) {
		orderItemService.deleteOrderItemById(id);
		return new ResponseEntity<>(new Gson().toJson("Success"), HttpStatus.OK);
	}
}
