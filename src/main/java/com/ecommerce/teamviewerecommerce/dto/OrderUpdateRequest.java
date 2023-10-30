package com.ecommerce.teamviewerecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "OrderDto Model Information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
	@NotEmpty
	public String billingAddress;
	@Schema(description = "Order Status - Placed, in Cart or Pending")
	@NotEmpty
	public String status;

}
