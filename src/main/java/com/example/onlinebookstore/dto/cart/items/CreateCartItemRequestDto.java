package com.example.onlinebookstore.dto.cart.items;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateCartItemRequestDto(
        @NotNull @Positive Long bookId,
        @NotNull @Positive Integer quantity) {
}
