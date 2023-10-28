package com.ecommerce.teamviewerecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Date;

@Schema(
        description = "Error Details Information"
)
public record ErrorDetails(Date timestamp, String message, String details) {
}
