package com.ecommerce.teamviewerecommerce.dto;

import com.ecommerce.teamviewerecommerce.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Schema(
        description = "OrderItemDto Model Information"
)
@Data
public class OrderItemDto {

    private Long Id;
    private Product productId;
    private Integer quantity;
    private BigDecimal unitPrice;
}
