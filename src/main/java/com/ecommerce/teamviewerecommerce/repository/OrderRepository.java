package com.ecommerce.teamviewerecommerce.repository;

import com.ecommerce.teamviewerecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
