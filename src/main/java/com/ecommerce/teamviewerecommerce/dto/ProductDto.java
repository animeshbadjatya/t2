package com.ecommerce.teamviewerecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    private Long id;
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
    @NotEmpty
    private BigDecimal unitPrice;
    @Schema(
            description = "Product Image Url"
    )
    private String imageUrl;
    @Schema(
            description = "Is Product Listing Active"
    )
    @NotEmpty
    private boolean active;

    @Schema(
            description = "Product Units"
    )
    @NotEmpty
    private int unitsInStock;
    @Schema(
            description = "Product Creation Date"
    )
    private Date dateCreated;
    @Schema(
            description = "Product Update Date"
    )
    private Date lastUpdated;
}
