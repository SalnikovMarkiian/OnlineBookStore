package com.example.onlinebookstore.dto.order;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreateOrderRequestDto(
        @NotBlank @Length(max = 128)
        String shippingAddress) {
}
