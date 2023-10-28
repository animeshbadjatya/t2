package com.ecommerce.teamviewerecommerce.repository;

import com.ecommerce.teamviewerecommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository not needed as JPA repo already has it.
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
