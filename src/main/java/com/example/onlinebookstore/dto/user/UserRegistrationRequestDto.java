package com.example.onlinebookstore.dto.user;

import com.example.onlinebookstore.validate.FieldsMatching;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@FieldsMatching(
        field = "password",
        fieldMatchWith = "verifyPassword",
        message = "Passwords do not match!"
)
public record UserRegistrationRequestDto(
        @NotBlank @Email
        String email,
        @NotBlank @Size(min = 2, max = 32)
        String firstName,
        @NotBlank @Size(min = 2, max = 32)
        String lastName,
        @NotBlank @Size(max = 128)
        String shippingAddress,
        @NotBlank @Size(min = 4, max = 32)
        String password,
        String verifyPassword) {
}
