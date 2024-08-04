package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContactCreateDto(
        @NotBlank
        @NotNull
        String name,
        @Email
        @NotBlank
        @NotNull
        String email,
        @NotBlank
        @NotNull
        String subject,
        @NotBlank
        @NotNull
        String message
) {
}
