package com.chatbotapp.chatbotapp.dto.authorization;

import lombok.Data;

public record RegisterRequestDTO(
        String email,
        String password,
        String name,
        String lastName,
        Integer age,
        String userName
) {
}
