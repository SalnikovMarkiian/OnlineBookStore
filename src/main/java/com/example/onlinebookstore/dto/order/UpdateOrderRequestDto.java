package com.example.onlinebookstore.dto.order;

import com.example.onlinebookstore.model.Order.Status;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderRequestDto(@NotNull Status status) {
}
