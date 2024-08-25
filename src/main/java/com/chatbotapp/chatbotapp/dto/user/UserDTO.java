package com.chatbotapp.chatbotapp.dto.user;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.OffsetDateTime;

public record UserDTO(
        Long id,
        OffsetDateTime dateCreated,
        OffsetDateTime dateModified,
        String name,
        String lastName,
        Integer age,
        String userName
) {
}
