package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectCreateDto(
        @NotBlank
        @NotNull
        String name,
        @NotBlank
        @NotNull
        String description,
        @NotBlank
        @NotNull
        String url,
        @NotBlank
        @NotNull
        String technologies
) {
}
