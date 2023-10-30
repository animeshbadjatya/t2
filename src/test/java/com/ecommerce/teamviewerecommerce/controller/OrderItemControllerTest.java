package com.ecommerce.teamviewerecommerce.controller;

import com.ecommerce.teamviewerecommerce.payload.OrderItemDto;
import com.ecommerce.teamviewerecommerce.service.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class OrderItemControllerTest {

    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private OrderItemController orderItemController;


    private OrderItemDto orderItemDto;

    @BeforeEach
    public void setup() {
        orderItemDto = new OrderItemDto();
        orderItemDto.setId(1L);
        orderItemDto.setProductId(121L);
        orderItemDto.setOrderId(21L);
        orderItemDto.setQuantity(2);
    }


    @DisplayName("JUnit test for Saving orderItem by ID operation")
    @Test
    public void givenOrderItem_whenSave_thenReturnOrderItem() {
        Mockito.when(orderItemService.createOrderItem(Mockito.any())).thenReturn(orderItemDto);
        ResponseEntity<OrderItemDto> result = orderItemController.createOrderItem(orderItemDto);
        Mockito.verify(orderItemService).createOrderItem(orderItemDto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(orderItemDto.getOrderId(), result.getBody().getOrderId());
    }

    @DisplayName("JUnit test for get orderItem by ID operation")
    @Test
    void givenOrderItemId_whenFindById_thenReturnOrderItem() {

        OrderItemDto orderItemDto = new OrderItemDto();
        Mockito.when(orderItemService.getOrderItemById(Mockito.anyLong())).thenReturn(orderItemDto);
        ResponseEntity<OrderItemDto> result = orderItemController.getOrderItemById(1L);
        Mockito.verify(orderItemService).getOrderItemById(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(orderItemDto.getQuantity(), result.getBody().getQuantity());
    }


    @DisplayName("JUnit test for get orderItem by ID operation")
    @Test
    void givenOrderItem_whenFindById_thenReturnUpdatedOrderItem() {
        orderItemDto.setQuantity(5);
        Mockito.when(orderItemService.updateOrderItemById(orderItemDto)).thenReturn(orderItemDto);
        ResponseEntity<OrderItemDto> updateOrderItemById = orderItemController.updateOrderItemById(orderItemDto);
        Mockito.verify(orderItemService).updateOrderItemById(orderItemDto);
        assertEquals(HttpStatus.OK, updateOrderItemById.getStatusCode());
        assertEquals(orderItemDto, updateOrderItemById.getBody());
    }

    @DisplayName("JUnit test for Delete orderItem by ID operation")
    @Test
    void givenOrderItem_whenFindById_thenReturnSuccessMsg() {

        Mockito.doNothing().when(orderItemService).deleteOrderItemById(Mockito.anyLong());
        ResponseEntity result = orderItemController.deleteOrderItemById(1L);
        Mockito.verify(orderItemService).deleteOrderItemById(1L);
        assertEquals(result.getBody(),"\"Success\"");


    }
}