package com.chatbotapp.chatbotapp.dto.authorization;

import lombok.Data;
import lombok.Getter;

public record LoginRequestDTO(
        String email,
        String password
) {
}
