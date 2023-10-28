package com.ecommerce.teamviewerecommerce.controller;

import com.ecommerce.teamviewerecommerce.dto.OrderDto;
import com.ecommerce.teamviewerecommerce.dto.OrderResponse;
import com.ecommerce.teamviewerecommerce.service.OrderService;
import com.ecommerce.teamviewerecommerce.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Orders"
)
@RestController
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
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto){

        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
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
    public ResponseEntity<OrderDto> updateOrderById(@RequestBody OrderDto orderDto, @PathVariable(name ="id") long id){
        OrderDto orderResponse = orderService.updateOrderById(orderDto,id);
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
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
