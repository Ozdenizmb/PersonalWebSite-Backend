package com.baranozdeniz.personalwebsite.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentWithUserDto(
        UUID id,
        UUID userId,
        String firstName,
        String lastName,
        String email,
        String imageUrl,
        String role,
        UUID projectId,
        String text,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
