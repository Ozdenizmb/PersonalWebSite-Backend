package com.baranozdeniz.personalwebsite.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDto(
        UUID id,
        UUID userId,
        UUID projectId,
        String text,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
