package com.baranozdeniz.personalwebsite.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ContactDto(
        UUID id,
        String name,
        String email,
        String subject,
        String message,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
