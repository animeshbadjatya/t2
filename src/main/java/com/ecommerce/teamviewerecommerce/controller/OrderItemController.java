package com.ecommerce.teamviewerecommerce.controller;

import com.ecommerce.teamviewerecommerce.dto.OrderItemDto;
import com.ecommerce.teamviewerecommerce.service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderItemController {

    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

//    @PostMapping("/products/{product}")
//    public ResponseEntity<OrderItemDto> createOrderItem(Long productId , OrderItemDto orderItemDto){
//
//    }
}
