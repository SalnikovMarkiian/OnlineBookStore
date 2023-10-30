package com.example.onlinebookstore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record CreateBookRequestDto(
        @NotBlank
        String title,
        @NotNull
        String author,
        @NotNull
        String isbn,
        @PositiveOrZero
        @NotNull
        BigDecimal price,
        String description,
        String coverImage
) {
}
