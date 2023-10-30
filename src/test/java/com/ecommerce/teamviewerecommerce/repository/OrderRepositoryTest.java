package com.ecommerce.teamviewerecommerce.repository;

import com.ecommerce.teamviewerecommerce.entity.Order;
import com.ecommerce.teamviewerecommerce.entity.OrderItem;
import com.ecommerce.teamviewerecommerce.entity.Product;

import com.ecommerce.teamviewerecommerce.payload.OrderItemDto;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private Order order;
    private OrderItem orderItem;
    private OrderItem orderItem1;

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setId(1L);
        order.setCustomerId(123L);
        order.setBillingAddress("123 Main St, City, Country");
        order.setStatus("Placed");
        order.setTotalAmount(250.0);

        List<OrderItem> orderItems = new ArrayList<>();

        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);


        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(product1);
        orderItem1.setQuantity(2);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(product2);
        orderItem2.setQuantity(3);

        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        order.setOrderItems(orderItems);
    }

    //JUnit test for save product operation
    @DisplayName("JUnit test to save Orders")
    @Test
    public void givenOrderObject_whenSave_thenReturnSavedOrder() {

        Order savedOrder = orderRepository.save(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isGreaterThan(0);

    }

    @DisplayName("JUnit test for get all order operation")
    @Test
    public void givenOrderList_whenFindAll_thenReturnOrderList() {


        Order order2 = new Order();
        order2.setId(2L);
        order2.setCustomerId(123L);
        order2.setBillingAddress("123 Main St, City, Country");
        order2.setStatus("Placed");
        order2.setTotalAmount(250.0);

        List<OrderItem> orderItems = new ArrayList<>();

        Product product1 = new Product();
        product1.setId(1L);

        Product product2 = new Product();
        product2.setId(2L);


        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(product1);
        orderItem1.setQuantity(2);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(product2);
        orderItem2.setQuantity(3);

        orderItems.add(orderItem1);
        orderItems.add(orderItem2);

        order2.setOrderItems(orderItems);


        Order savedOrder1 = orderRepository.save(order);
        Order savedOrder2 = orderRepository.save(order2);
        ;

        List<Order> orderList = orderRepository.findAll();

        assertThat(orderList).isNotNull();
        assertThat(orderList.size()).isEqualTo(2);

    }

    @DisplayName("JUnit test for get Order by ID operation")
    @Test
    public void givenOrderObject_whenFindById_thenReturnOrderObject() {

        Order savedOrder = orderRepository.save(order);

        Order orderDb = orderRepository.findById(savedOrder.getId()).get();
        assertThat(orderDb).isNotNull();
    }

    @DisplayName("JUnit test for Update Order operation")
    @Test
    public void givenOrderObject_whenUpdated_thenReturnUpdatedOrder() {


        Order savedOrder = orderRepository.save(order);

        Order updateOrder = orderRepository.findById(savedOrder.getId()).get();
        updateOrder.setBillingAddress("123 CA");

        Order savedUpdatedOrder = orderRepository.save(updateOrder);

        assertThat(savedUpdatedOrder.billingAddress).isEqualTo("123 CA");
    }

    @DisplayName("JUnit test for Delete Order operation")
        @Test
        public void givenOrderObject_whenDeleted_thenRemoveOrder(){

            Order savedOrder = orderRepository.save(order);
            orderRepository.deleteById(order.getId());

            Optional<Order> orderOptional = orderRepository.findById(order.getId());

            assertThat(orderOptional).isEmpty();
        }


    }


