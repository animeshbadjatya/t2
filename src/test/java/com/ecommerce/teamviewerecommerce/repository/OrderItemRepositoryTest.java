package com.ecommerce.teamviewerecommerce.repository;

import com.ecommerce.teamviewerecommerce.entity.Order;
import com.ecommerce.teamviewerecommerce.entity.OrderItem;
import com.ecommerce.teamviewerecommerce.entity.Product;
import com.ecommerce.teamviewerecommerce.payload.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;
    private OrderItem orderItem;
    @BeforeEach
    public void setup(){

        Order order = new Order();
        order.setId(1L);

        Product product = new Product();
        product.setId(2L);
        product.setName("Test");


        orderRepository.save(order);
        productRepository.save(product);
        orderItem = new OrderItem();


        orderItem.setId(10L);
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        orderItem.setQuantity(50);
    }

    @DisplayName("JUnit test for save orderItem operation")
    @Test
    public void givenOrderItemObject_whenSave_thenReturnSavedOrderItem(){
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        assertThat(savedOrderItem).isNotNull();
        assertThat(savedOrderItem.getId()).isGreaterThan(0);

    }

    @DisplayName("JUnit test for get all OrderItems operation")
    @Test
    public void givenOrderItemList_whenFindAll_thenReturnOrderItemList(){
        OrderItem savedFirstOrderItem = orderItemRepository.save(orderItem);
 //       OrderItem savedSecondOrderItem = orderItemRepository.save(orderItem2);

        List<OrderItem> orderItemList = orderItemRepository.findAll();

        assertThat(orderItemList).isNotNull();
        assertThat(orderItemList.size()).isEqualTo(1);

    }

    @DisplayName("JUnit test for get orderItem by ID operation")
    @Test
    public void givenOrderItemObject_whenFindById_thenReturnorderItemObject(){

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        OrderItem orderItemDb = orderItemRepository.findById(savedOrderItem.getId()).get();
        assertThat(orderItemDb).isNotNull();
    }

    @DisplayName("JUnit test for Update orderItem operation")
    @Test
    public void givenOrderItemObject_whenUpdated_thenReturnUpdatedOrderItem(){


        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        OrderItem updateOrderItem = orderItemRepository.findById(savedOrderItem.getId()).get();
        updateOrderItem.setQuantity(2);

        OrderItem savedUpdatedOrderItem = orderItemRepository.save(updateOrderItem);

        assertThat(savedUpdatedOrderItem.getQuantity()).isEqualTo(2);
    }

    @DisplayName("JUnit test for Delete OrderItem operation")
    @Test
    public void givenOrderItemObject_whenDeleted_thenRemoveOrderItem(){

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        orderItemRepository.deleteById(orderItem.getId());

        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderItem.getId());

        assertThat(orderItemOptional).isEmpty();
    }


}
