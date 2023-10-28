package com.ecommerce.teamviewerecommerce.service.impl;

import com.ecommerce.teamviewerecommerce.dto.OrderDto;
import com.ecommerce.teamviewerecommerce.dto.OrderItemDto;
import com.ecommerce.teamviewerecommerce.dto.OrderResponse;
import com.ecommerce.teamviewerecommerce.entity.Order;
import com.ecommerce.teamviewerecommerce.entity.OrderItem;
import com.ecommerce.teamviewerecommerce.exception.ResourceNotFoundException;
import com.ecommerce.teamviewerecommerce.repository.OrderRepository;
import com.ecommerce.teamviewerecommerce.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private ModelMapper mapper;
    private  OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        Order order = mapper.map(orderDto, Order.class);
        // Convert DTO to entity
//        Order order = new Order();
//        order.setTotalQuantity(orderDto.getTotalQuantity());
//        order.setCustomerId(orderDto.getCustomerId());
//        order.setTotalPrice(orderDto.getTotalPrice());
//        order.setBillingAddress(orderDto.getBillingAddress());
//        order.setStatus(orderDto.getStatus());
//
//        // Map order items from OrderDTO to OrderEntity
//        List<OrderItem> orderItemEntities = new ArrayList<>();
//        for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
//            OrderItem orderItem = new OrderItem();
//        //    orderItem.setProductId(orderItemDto.getProductId()); // Assuming you have a 'product' field in OrderItemEntity
//            orderItem.setQuantity(orderItemDto.getQuantity());
//            orderItem.setOrder(order); // Set the relationship to the order
//            orderItemEntities.add(orderItem);
//        }
//        order.setOrderItems(orderItemEntities);

        System.out.println("****OrderDTO" + orderDto.toString());
        Order newOrder = orderRepository.save(order);

//        //convert entity to DTO to return it to controller
//        OrderDto orderResponse = new OrderDto();
//        orderResponse.setId(newOrder.getId());
//        orderResponse.setTotalQuantity(newOrder.getTotalQuantity());
//        orderResponse.setCustomerId(newOrder.getCustomerId());
//        orderResponse.setTotalPrice(newOrder.getTotalPrice());
//        orderResponse.setBillingAddress(newOrder.getBillingAddress());
//        orderResponse.setStatus(newOrder.getStatus());
//        orderResponse.setDateCreated(newOrder.getDateCreated());
//        orderResponse.setLastUpdated(newOrder.getLastUpdated());
//
//        List<OrderItemDto> savedOrderItems = new ArrayList<>();
//        for (OrderItem orderItemEntity : newOrder.getOrderItems()) {
//            OrderItemDto savedOrderItemDTO = new OrderItemDto();
//          //  savedOrderItemDTO.setProductId(orderItemEntity.getProductId());
//            savedOrderItemDTO.setQuantity(orderItemEntity.getQuantity());
//            savedOrderItems.add(savedOrderItemDTO);
//        }
//        orderResponse.setOrderItems(savedOrderItems);
       // OrderDto orderResponse = mapper.map(order, OrderDto.class);
        return mapper.map(order, OrderDto.class);

    }

    @Override
    public OrderResponse getAllOrders(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Order> orders = orderRepository.findAll(pageable);

        // Get content from Page object
        List<Order> listOfProducts = orders.getContent();

        List<OrderDto> content = listOfProducts.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setContent(content);
        orderResponse.setPageNo(orders.getNumber());
        orderResponse.setPageSize(orders.getSize());
        orderResponse.setTotalElements(orders.getTotalElements());
        orderResponse.setTotalPages(orders.getTotalPages());
        orderResponse.setLast(orders.isLast());


        return orderResponse;
    }

    @Override
    public OrderDto getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Order", "id", id);
        });
        return mapToDTO(order);
    }

    @Override
    public OrderDto updateOrderById(OrderDto orderDto, long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Order", "id", id);
        });
//        order.setTotalQuantity(orderDto.getTotalQuantity());
//        order.setCustomerId(orderDto.getCustomerId());
//        order.setTotalPrice(orderDto.getTotalPrice());
//        order.setBillingAddress(orderDto.getBillingAddress());
//        order.setStatus(orderDto.getStatus());
        Order order1 = mapper.map(orderDto, Order.class);

        Order updatedOrder = orderRepository.save(order1);
        return mapToDTO(updatedOrder);
    }

    @Override
    public void deleteOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Order", "id", id);
        });
        orderRepository.delete(order);

    }

    private OrderDto mapToDTO(Order order){
        OrderDto orderDto = mapper.map(order, OrderDto.class);
//        OrderDto orderResponse = new OrderDto();
//        orderResponse.setId(order.getId());
//        orderResponse.setTotalQuantity(order.getTotalQuantity());
//        orderResponse.setCustomerId(order.getCustomerId());
//        orderResponse.setTotalPrice(order.getTotalPrice());
//        orderResponse.setBillingAddress(order.getBillingAddress());
//        orderResponse.setStatus(order.getStatus());
//        orderResponse.setDateCreated(order.getDateCreated());
//        orderResponse.setLastUpdated(order.getLastUpdated());
        return orderDto;
    }
}
