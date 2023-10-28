package com.ecommerce.teamviewerecommerce.controller;

import com.ecommerce.teamviewerecommerce.dto.OrderDto;
import com.ecommerce.teamviewerecommerce.dto.OrderResponse;
import com.ecommerce.teamviewerecommerce.dto.ProductDto;
import com.ecommerce.teamviewerecommerce.dto.ProductResponse;
import com.ecommerce.teamviewerecommerce.service.OrderService;
import com.ecommerce.teamviewerecommerce.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Create order API
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto){

        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<OrderResponse> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ){
        return new ResponseEntity<>(orderService.getAllOrders(pageNo, pageSize), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name ="id") long id){
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrderById(@RequestBody OrderDto orderDto, @PathVariable(name ="id") long id){
        OrderDto orderResponse = orderService.updateOrderById(orderDto,id);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById( @PathVariable(name ="id") long id){
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
