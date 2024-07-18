package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserUpdateDto(
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        String password,
        String biography,
        String phoneNumber,
        String profession,
        LocalDate birthday
) {
}
