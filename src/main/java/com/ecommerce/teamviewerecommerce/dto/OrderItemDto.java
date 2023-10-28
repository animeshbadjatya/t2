package com.ecommerce.teamviewerecommerce.dto;

import com.ecommerce.teamviewerecommerce.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {

    private Long Id;
    private Product productId;
    private Integer quantity;
    private BigDecimal unitPrice;
}
