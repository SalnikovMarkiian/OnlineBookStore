package com.example.onlinebookstore.dto.order;

public record OrderItemDto(
        Long id,
        Long bookId,
        Integer quantity) {
}
