package com.chatbotapp.chatbotapp.dto.user;

import lombok.NonNull;

public record UserCreationDTO(
        @NonNull String name,
        @NonNull String lastName,
        @NonNull Integer age,
        @NonNull String userName
) {
}
