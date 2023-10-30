package com.ecommerce.teamviewerecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Schema(
        description = "ProductDto Model Information"
)
@Data
public class ProductDto {

    @Schema(
            description = "Product Name"
    )
    @NotEmpty
    @Size(min =2, message = "Product name should have at least 2 characters")
    private String name;

    @Schema(
            description = "Product Description"
    )
    @NotEmpty
    @Size(min =2, message = "Product description should have at least 2 characters")
    private String description;

    @Schema(
            description = "Product Price"
    )
    @Min(value = 0L, message = "The unitPrice must be positive")
    private Double unitPrice;

    @Schema(
            description = "Product Image Url"
    )
    private String imageUrl;


    @Schema(
            description = "Product Units"
    )
    @Min(value = 0L, message = "The unitsInStock must be positive")
    private int unitsInStock;
}
