package com.example.onlinebookstore.dto.shopping.cart;

import jakarta.validation.constraints.Positive;

public record CartItemUpdateRequestDto(@Positive int quantity) {
}
