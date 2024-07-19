package com.baranozdeniz.personalwebsite.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentUpdateDto(
        @NotNull
        String text
) {
}
