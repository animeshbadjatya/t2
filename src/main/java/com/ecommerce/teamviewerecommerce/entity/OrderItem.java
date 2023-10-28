package com.ecommerce.teamviewerecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="unit_price")
    private BigDecimal unitPrice;

}