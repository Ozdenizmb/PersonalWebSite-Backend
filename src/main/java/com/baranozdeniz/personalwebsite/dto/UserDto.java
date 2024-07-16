package com.baranozdeniz.personalwebsite.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String imageUrl,
        String biography,
        String profession,
        LocalDate birthday,
        String role,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
