package com.baranozdeniz.personalwebsite.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProjectDto(
        UUID id,
        String name,
        String description,
        String url,
        String technologies,
        String imageUrl,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
