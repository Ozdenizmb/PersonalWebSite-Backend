package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.NotNull;

public record ProjectCreateDto(
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        String url,
        @NotNull
        String technologies,
        @NotNull
        String imageUrl
) {
}
