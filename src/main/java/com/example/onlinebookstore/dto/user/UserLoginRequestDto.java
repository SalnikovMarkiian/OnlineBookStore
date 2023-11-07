package com.example.onlinebookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @NotBlank @Email
        String email,
        @NotBlank @Size(
                min = 4,
                max = 32,
                message = "password must contain from 2 to 32 characters"
        )
        String password) {
}
