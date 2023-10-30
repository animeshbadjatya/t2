package com.ecommerce.teamviewerecommerce.controller;import com.ecommerce.teamviewerecommerce.entity.Order;
import com.ecommerce.teamviewerecommerce.entity.OrderItem;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.payload.OrderDto;
import com.ecommerce.teamviewerecommerce.payload.OrderItemDto;
import com.ecommerce.teamviewerecommerce.payload.OrderUpdateRequest;
import com.ecommerce.teamviewerecommerce.payload.ProductDto;
import com.ecommerce.teamviewerecommerce.service.OrderService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderControllerTest {

    private OrderService orderService;
    private OrderController orderController;
    private OrderDto orderDto;
    @Before
    public void setup() {
        orderService = Mockito.mock(OrderService.class);
        orderController = new OrderController(orderService);

        orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setCustomerId(123L);
        orderDto.setBillingAddress("123 Main St, City, Country");
        orderDto.setStatus("Placed");
        orderDto.setTotalAmount(250.0);

        List<OrderItemDto> orderItems = new ArrayList<>();

        OrderItemDto orderItemDto1 = new OrderItemDto();
        orderItemDto1.setOrderId(1L);

        orderItemDto1.setQuantity(2);

        OrderItemDto orderItemDto2 = new OrderItemDto();
        orderItemDto2.setOrderId(2L);
        orderItemDto2.setQuantity(3);
        orderItemDto2.setProductId(4L);

        orderItems.add(orderItemDto1);
        orderItems.add(orderItemDto2);

        orderDto.setOrderItems(orderItems);
    }

    @DisplayName("JUnit test for save Order ")
    @Test
    public void givenOrder_whenSave_thenReturnOrder() {

        Mockito.when(orderService.placeOrder(orderDto)).thenReturn(orderDto);
        ResponseEntity<OrderDto> result = orderController.placeOrder(orderDto);
        Mockito.verify(orderService).placeOrder(orderDto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(orderDto.getOrderItems(), result.getBody().getOrderItems());
    }


    @DisplayName("JUnit test for get order by ID operation")
    @Test
    public void givenOrderId_whenFindById_thenReturnOrder() {
        Mockito.when(orderService.getOrderById(Mockito.anyLong())).thenReturn(orderDto);
        ResponseEntity<OrderDto> result = orderController.getOrderById(1L);
        Mockito.verify(orderService).getOrderById(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(orderDto, result.getBody());
    }



    @DisplayName("JUnit test for Delete Order by ID operation")
    @Test
    public void givenOrder_whenFindById_thenReturnSuccessMsg() {

        Mockito.doNothing().when(orderService).deleteOrderById(Mockito.anyLong());
        ResponseEntity result = orderController.deleteOrderById(1L);
        Mockito.verify(orderService).deleteOrderById(1L);
        assertThat(result.getBody()).isNotNull();


    }
}