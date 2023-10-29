package com.ecommerce.teamviewerecommerce.repository;

import com.ecommerce.teamviewerecommerce.entity.Order;
import com.ecommerce.teamviewerecommerce.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    //JUnit test for save product operation
    @DisplayName("JUnit test to save Orders")
    @Test
    public void givenOrderObject_whenSave_thenReturnSavedOrder(){

        Order order = new Order();
        order.setId(888L);
        order.setBillingAddress("assets/images/products/test.png");
        order.setTotalPrice(BigDecimal.valueOf(32.34));
        order.setTotalQuantity(5);
        order.setOrderItemsId(100L);


        Order savedOrder = orderRepository.save(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isGreaterThan(0);

    }
}
