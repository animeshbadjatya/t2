package com.ecommerce.teamviewerecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequest {
	private Long orderId;
	private Long productId;
	private int quantity;
	private Long customerId;
	private String billingAddress;

}
