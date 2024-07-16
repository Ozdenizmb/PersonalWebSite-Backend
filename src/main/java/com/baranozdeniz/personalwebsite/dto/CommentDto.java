package com.baranozdeniz.personalwebsite.dto;

import java.util.UUID;

public record CommentDto(
        UUID id,
        UUID userId,
        UUID projectId,
        String text
) {
}
