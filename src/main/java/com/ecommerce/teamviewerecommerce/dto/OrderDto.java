package com.ecommerce.teamviewerecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Schema(
        description = "OrderDto Model Information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;


//    @Schema(
//            description = "List of Products to Order"
//    )
//    private List<OrderItemDto> orderItems;
    @Schema(
            description = "Total Quantity of Order"
    )
    @NotEmpty
    public int totalQuantity;

    @Schema(
            description = "Total Price of Order"
    )
    @NotEmpty
    private BigDecimal totalPrice;

    @Schema(
            description = "Customer ID for the Order"
    )
    @NotEmpty
    public Long customerId;

    @Schema(
            description = "Billing Address of Order"
    )
    @NotEmpty
    public String billingAddress;
    @Schema(
            description = "Order Status - Placed, in Cart or Pending"
    )
    @NotEmpty
    public String status;
    @Schema(
            description = "Order Creation Date"
    )
    @NotEmpty
    private Date dateCreated;
    @Schema(
            description = "Order Update Date"
    )
    @NotEmpty
    private Date lastUpdated;
}
