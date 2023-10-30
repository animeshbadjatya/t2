package com.ecommerce.teamviewerecommerce.service;

import com.ecommerce.teamviewerecommerce.entity.Order;
import com.ecommerce.teamviewerecommerce.entity.OrderItem;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.exception.ResourceNotFoundException;
import com.ecommerce.teamviewerecommerce.payload.OrderItemDto;
import com.ecommerce.teamviewerecommerce.payload.OrderItemResponse;
import com.ecommerce.teamviewerecommerce.repository.OrderItemRepository;
import com.ecommerce.teamviewerecommerce.repository.OrderRepository;
import com.ecommerce.teamviewerecommerce.repository.ProductRepository;
import com.ecommerce.teamviewerecommerce.service.impl.OrderItemServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderItemServiceTest {
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderItemServiceImpl orderItemService;

    @Autowired
    private ModelMapper modelMapper;

    private OrderItemDto orderItemDto;

    private Product product;
    @Before
    public void setup() {
        orderItemRepository = mock(OrderItemRepository.class);
        productRepository = mock(ProductRepository.class);
        orderRepository = mock(OrderRepository.class);
        modelMapper = new ModelMapper();
        orderItemService = new OrderItemServiceImpl(orderItemRepository, productRepository, orderRepository, modelMapper);

        orderItemDto = new OrderItemDto();
        orderItemDto.setId(1L);
        orderItemDto.setProductId(10L);
        orderItemDto.setOrderId(1L);
        orderItemDto.setQuantity(2);

        product = new Product();
        product.setId(10L);
        product.setName("Apple");
        product.setUnitPrice(23.99);
    }

    @Test
    public void testCreateOrderItem_Success() {
        // Given

        Order order = new Order();
        order.setId(1L);
        order.setCustomerId(23242L);
        when(productRepository.findById(orderItemDto.getProductId())).thenReturn(Optional.of(product));
        when(orderRepository.findById(orderItemDto.getOrderId())).thenReturn(Optional.of(order));

        // When
        OrderItemDto createdOrderItem = orderItemService.createOrderItem(orderItemDto);

        // Then
        assertNotNull(createdOrderItem);
    }

    @Test
    public void testCreateOrderItem_ProductNotFound() {
        System.out.println(orderItemDto.toString());
        // Given
        when(productRepository.findById(orderItemDto.getProductId())).thenReturn(Optional.empty());

        // When and Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> orderItemService.createOrderItem(orderItemDto));
        assertEquals("Product not found with id : '" + orderItemDto.getProductId()+"'", exception.getMessage());
    }

    @Test
    public void testCreateOrderItem_OrderNotFound() {
        // Given
        when(productRepository.findById(orderItemDto.getProductId())).thenReturn(Optional.of(product));
        when(orderRepository.findById(orderItemDto.getOrderId())).thenReturn(Optional.empty());

        // When and Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> orderItemService.createOrderItem(orderItemDto));
        assertEquals("Order is not present in table Orders not found with id : '" + orderItemDto.getOrderId()+"'", exception.getMessage());
    }


}
