package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentUpdateDto(
        @NotNull
        UUID userId,
        @NotNull
        UUID projectId,
        @NotNull
        String text
) {
}
