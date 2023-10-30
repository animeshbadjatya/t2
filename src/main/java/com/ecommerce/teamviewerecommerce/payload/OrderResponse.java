package com.ecommerce.teamviewerecommerce.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(
        description = "OrderResponse Information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private List<OrderDto> content;
    private  int pageNo;
    private  int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
