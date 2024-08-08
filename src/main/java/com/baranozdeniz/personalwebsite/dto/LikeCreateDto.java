package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LikeCreateDto(
        @NotNull
        UUID userId,
        @NotNull
        UUID projectId
) {
}
