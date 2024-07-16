package com.baranozdeniz.personalwebsite.dto;

import java.util.UUID;

public record LikeDto(
        UUID id,
        UUID userId,
        UUID projectId
) {
}
