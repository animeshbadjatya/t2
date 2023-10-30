package com.ecommerce.teamviewerecommerce.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(
        description = "ProductDto Model Information"
)
@Data
public class ProductDto {

    @Schema(
            description = "Id"
    )
    private Long Id;
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
