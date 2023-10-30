package com.ecommerce.teamviewerecommerce.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Schema(
        description = "OrderDto Model Information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @Schema(
            description = "ID"
    )
    private Long id;

    @Schema(
            description = "List of Products to Order"
    )
    private List<OrderItemDto> orderItems;

    @Schema(
            description = "Total Amount of Order"
    )
    private Double totalAmount;

    @Schema(
            description = "Customer ID for the Order"
    )
    @Min(value = 0L, message = "The customerId must be positive")
    public Long customerId;

    @Schema(
            description = "Billing Address of Order"
    )
    @Size(min =5, message = "Billing address should have at least 5 characters")
    public String billingAddress;

    @Schema(
            description = "Order Status - Placed, in Cart or Pending"
    )
    @NotEmpty
    public String status;
    @Schema(
            description = "Order Creation Date"
    )
    private Date dateCreated;
    @Schema(
            description = "Order Update Date"
    )
    private Date lastUpdated;
}
