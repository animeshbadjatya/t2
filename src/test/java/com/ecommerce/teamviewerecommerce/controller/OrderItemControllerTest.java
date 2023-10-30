package com.ecommerce.teamviewerecommerce.controller;

import com.ecommerce.teamviewerecommerce.payload.OrderItemDto;
import com.ecommerce.teamviewerecommerce.service.OrderItemService;
import org.junit.jupiter.api.BeforeEach;
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


    @Test
    public void createOrderItem() {
        System.out.println("hello");
        Mockito.when(orderItemService.createOrderItem(Mockito.any())).thenReturn(orderItemDto);
        ResponseEntity result = orderItemController.createOrderItem(orderItemDto);
//
        Mockito.verify(orderItemService).createOrderItem(orderItemDto);
        // Add assertions to compare the expected and actual results
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(orderItemDto, result.getBody());
    }

//    @Test
//    void getAllOrderItem() {
//        OrderItemDto orderItemDto1;
//        orderItemDto1 = new OrderItemDto();
//        orderItemDto1.setId(888L);
//        orderItemDto1.setProductId(1L);
//        orderItemDto1.setOrderId(21L);
//        orderItemDto1.setQuantity(2);
//
//        OrderItemDto orderItemDto2;
//        orderItemDto2 = new OrderItemDto();
//        orderItemDto2.setId(888L);
//        orderItemDto2.setProductId(1L);
//        orderItemDto2.setOrderId(21L);
//        orderItemDto2.setQuantity(2);
//        // Create a sample Page of OrderItemDto for the test
//        List<OrderItemDto> orderItemDtoList = Arrays.asList(orderItemDto1,orderItemDto2);
//        Page<OrderItemDto> orderItemDtoPage = new PageImpl<>(orderItemDtoList);
//
//        // Mock the behavior of orderItemService.getAllOrderItems
//        Mockito.when(orderItemService.getAllOrderItems(Mockito.any())).thenReturn(orderItemDtoPage);
//
//        // Define a Pageable for pagination
//        Pageable pageable = PageRequest.of(0, 10); // Page 1 with 10 items per page
//
//        // Call the method under test with pageable
//        ResponseEntity result = orderItemController.getAllOrderItems(pageable);
//
//        // Verify that getAllOrderItems was called with the expected pageable
//        Mockito.verify(orderItemService).getAllOrderItems(pageable);
//
//        // Add assertions to compare the expected and actual results
//        assertEquals(HttpStatus.OK, result.getStatusCode());  // Replace with your expected status code
//        assertEquals(orderItemDtoPage.getContent(), result.getBody());  // Replace with your expected response body
//    }

    @Test
    void getOrderItemById() {
// Create a sample OrderItemDto for the test
        OrderItemDto orderItemDto = new OrderItemDto(/* Initialize with test data */);

        // Mock the behavior of orderItemService.getOrderItemById
        Mockito.when(orderItemService.getOrderItemById(Mockito.anyLong())).thenReturn(orderItemDto);

        // Call the method under test with a specific ID
        ResponseEntity<OrderItemDto> result = orderItemController.getOrderItemById(1L); // Replace 1L with the desired ID

        // Verify that getOrderItemById was called with the expected ID
        Mockito.verify(orderItemService).getOrderItemById(1L); // Replace 1L with the desired ID

        // Add assertions to compare the expected and actual results
        assertEquals(HttpStatus.OK, result.getStatusCode());  // Replace with your expected status code
        assertEquals(orderItemDto, result.getBody());  // Replace with your expected response body
    }

    @Test
    void updateOrderItemById() {
        orderItemDto.setQuantity(5);

        Mockito.when(orderItemService.updateOrderItemById(orderItemDto)).thenReturn(orderItemDto);

        // Call the method under test with the updated OrderItemDto
        ResponseEntity<OrderItemDto> updateOrderItemById = orderItemController.updateOrderItemById(orderItemDto);

        // Verify that updateOrderItemById was called with the updated OrderItemDto
        Mockito.verify(orderItemService).updateOrderItemById(orderItemDto);
        // Add assertions to compare the expected and actual results
        assertEquals(HttpStatus.OK, updateOrderItemById.getStatusCode());  // Replace with your expected status code
        assertEquals(orderItemDto, updateOrderItemById.getBody());  // Replace with your expected response body
    }

    @Test
    void deleteOrderItemById() {

        // Mock the behavior of orderItemService.deleteOrderItemById
        Mockito.doNothing().when(orderItemService).deleteOrderItemById(Mockito.anyLong());

        // Call the method under test with a specific ID
        ResponseEntity result = orderItemController.deleteOrderItemById(1L); // Replace 1L with the desired ID

        // Verify that deleteOrderItemById was called with the expected ID
        Mockito.verify(orderItemService).deleteOrderItemById(1L); // Replace 1L with the desired ID

        // Add assertions to compare the expected and actual results
    //    assertEquals(HttpStatus.OK, result.getStatusCode());  // Replace with your expected status code
        assertEquals(result.getBody(),"Success");  // Verify that the re


    }
}