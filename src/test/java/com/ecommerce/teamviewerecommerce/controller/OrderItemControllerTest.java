//package com.ecommerce.teamviewerecommerce.controller;
//
//import com.ecommerce.teamviewerecommerce.dto.OrderItemDto;
//import com.ecommerce.teamviewerecommerce.entity.Product;
//import com.ecommerce.teamviewerecommerce.service.OrderItemService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.http.ResponseEntity;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class OrderItemControllerTest {
//
//    @Mock
//    private OrderItemService orderItemService;
//
//    private OrderItemController orderItemController;
//    private OrderItemDto orderItemDto;
//    @BeforeEach
//    public void setup(){
//        orderItemDto = new OrderItemDto();
//        orderItemDto.setId(888L);
//        orderItemDto.setProductId(1L);
//        orderItemDto.setOrderId(21L);
//        orderItemDto.setQuantity(2);
//
//
//    @Test
//    void createOrderItem() {
////        Mockito.when(orderItemService.createOrderItem(Mockito.any())).thenReturn(orderItemDto);
////        ResponseEntity result = orderItemController.createOrderItem(orderItemDto);
////
////        Mockito.verify(orderItemService.createOrderItem);
// //   }
//    }
//
//    @Test
//    void getAllOrderItem() {
//    }
//
//    @Test
//    void getOrderItemById() {
//    }
//
//    @Test
//    void updateOrderById() {
//    }
//
//    @Test
//    void deleteOrderById() {
//    }
//}