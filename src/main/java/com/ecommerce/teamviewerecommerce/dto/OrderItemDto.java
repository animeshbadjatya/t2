package com.ecommerce.teamviewerecommerce.dto;

import com.ecommerce.teamviewerecommerce.entity.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Schema(
        description = "OrderItemDto Model Information"
)
@Data
@Setter
@Getter
public class OrderItemDto {

    private Long Id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
}
