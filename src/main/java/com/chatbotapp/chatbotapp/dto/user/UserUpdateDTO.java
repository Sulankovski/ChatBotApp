package com.chatbotapp.chatbotapp.dto.user;

import lombok.NonNull;

public record UserUpdateDTO(
        @NonNull String userName
) {
}
