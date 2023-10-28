package com.ecommerce.teamviewerecommerce.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private List<OrderItemDto> orderItems;
    public int totalQuantity;
    private BigDecimal totalPrice;
    public Long customerId;
    public String billingAddress;
    public String status;
    private Date dateCreated;
    private Date lastUpdated;
}
