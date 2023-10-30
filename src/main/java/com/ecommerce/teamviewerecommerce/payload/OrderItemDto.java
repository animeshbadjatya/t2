package com.ecommerce.teamviewerecommerce.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
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

    @Schema(
            description = "Id"
    )
    private Long Id;

    @Schema(
            description = "Order ID"
    )
    @Min(value = 0L, message = "The orderId must be positive")
    private Long orderId;
    @Schema(
            description = "Product ID"
    )
    @Min(value = 0L, message = "The productId must be positive")
    private Long productId;
    @Schema(
            description = "Quantity"
    )
    @Min(value = 0L, message = "The quantity must be positive")
    private Integer quantity;

}
