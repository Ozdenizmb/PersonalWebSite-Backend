package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserCreateDto(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        String email,
        @NotNull
        String password,
        String imageUrl,
        String biography,
        String profession,
        LocalDate birthday
) {
}
