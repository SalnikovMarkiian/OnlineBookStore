package com.example.onlinebookstore.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequestDto(
        @NotBlank
        @Size(
                max = 30,
                message = "The name must contain no more than 30 characters"
        )
        String name,
        @Size(
                max = 255,
                message = "The description must contain no more than 255 characters"
        )
        String description
) {
}
