package com.ecommerce.teamviewerecommerce.service.impl;

import com.ecommerce.teamviewerecommerce.dto.OrderItemDto;
import com.ecommerce.teamviewerecommerce.entity.OrderItem;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.exception.ResourceNotFoundException;
import com.ecommerce.teamviewerecommerce.repository.OrderItemRepository;
import com.ecommerce.teamviewerecommerce.repository.ProductRepository;
import com.ecommerce.teamviewerecommerce.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;
    private ProductRepository productRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderItemDto createOrderItem(Long productId, OrderItemDto orderItemDto) {
        OrderItem orderItem = mapToEntity(orderItemDto);
        // Retrieve product entity by Id
        Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product", "id", productId));

        // Set product to OrderItem entity
        // orderItem.setProductId(product);

        // OrderItem save to DB
        OrderItem newOrderItem = orderItemRepository.save(orderItem);

        return mapToDto(newOrderItem);
    }

    private OrderItemDto mapToDto(OrderItem orderItem){
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
     //   orderItemDto.getProduct(orderItem.getProduct())
        orderItemDto.setUnitPrice(orderItem.getUnitPrice());
    return orderItemDto;
    }

    private OrderItem mapToEntity(OrderItemDto orderItemDto){
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        //   orderItem.getProduct(orderItem.getProduct())
        orderItem.setUnitPrice(orderItemDto.getUnitPrice());
        return orderItem;

    }
}
