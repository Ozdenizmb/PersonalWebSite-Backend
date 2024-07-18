package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.NotNull;

public record ProjectUpdateDto(
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        String url,
        @NotNull
        String technologies
) {
}
