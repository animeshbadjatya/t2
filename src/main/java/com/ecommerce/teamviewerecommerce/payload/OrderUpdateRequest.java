package com.ecommerce.teamviewerecommerce.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "OrderUpdateDto Model Information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
	@NotEmpty
	public String billingAddress;
	@Schema(description = "Order Status - Placed or Pending")
	@NotEmpty
	public String status;

}
