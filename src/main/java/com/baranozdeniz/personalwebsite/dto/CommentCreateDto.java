package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentCreateDto(
        @NotBlank
        @NotNull
        UUID userId,
        @NotBlank
        @NotNull
        UUID projectId,
        @NotBlank
        @NotNull
        String text
) {
}
