package com.ecommerce.teamviewerecommerce.controller;

import com.ecommerce.teamviewerecommerce.payload.OrderResponse;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.teamviewerecommerce.payload.OrderDto;
import com.ecommerce.teamviewerecommerce.payload.OrderUpdateRequest;
import com.ecommerce.teamviewerecommerce.service.OrderService;
import com.ecommerce.teamviewerecommerce.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "CRUD REST APIs for Orders"
)
@RestController

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Create Order REST API",
            description = "This API created the Orders in the Orders table",
             responses = {
                     @ApiResponse(
                             responseCode = "201",
                             description = "Http Status 201 created."
                     )
             }
    )

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {
    	OrderDto order = orderService.placeOrder(orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get the list of Orders REST API",
            description = "This API get the Orders with Pagination",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "This API returns all the Orders with Page Number starting from 0 and having Default Page Size of 10"
                    )
            }
    )

    @GetMapping
    public ResponseEntity<OrderResponse> getAllOrders(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ){
        return new ResponseEntity<>(orderService.getAllOrders(pageNo, pageSize), HttpStatus.OK);

    }

    @Operation(
            summary = "Get the Order with the specified ID",
            description = "This API get the Order with specified ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "This API returns the Order with the specified ID"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "This API returns 404, if a Product is not found"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "This API returns 400, if inventory of the product is than requested"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name ="id") long id){
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Update the Order with the specified ID",
            description = "This API updates the Order with specified ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "This API returns the updated Order with the specified ID"
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrderById(@RequestBody OrderUpdateRequest orderUpdateRequest, @PathVariable(name ="id") long id){
        OrderDto orderResponse = orderService.updateOrderById(orderUpdateRequest,id);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Deletes the Order with the specified ID",
            description = "This API deletes the Order with specified ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "This API returns Success if the Order was deleted"
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById( @PathVariable(name ="id") long id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(new Gson().toJson("Success"), HttpStatus.OK);
    }
	
	
}
