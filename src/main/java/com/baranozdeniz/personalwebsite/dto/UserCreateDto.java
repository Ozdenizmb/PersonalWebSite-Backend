package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserCreateDto(
        @NotBlank
        @NotNull
        String firstName,
        @NotBlank
        @NotNull
        String lastName,
        @NotBlank
        @NotNull
        @Email(message = "Invalid email format.")
        String email,
        @NotBlank
        @NotNull
        String password,
        String biography,
        String phoneNumber,
        String profession,
        LocalDate birthday
) {
}
