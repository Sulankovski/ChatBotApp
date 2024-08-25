package com.chatbotapp.chatbotapp.dto.chat_room;

import lombok.NonNull;

public record ChatRoomCreationDTO(
        @NonNull Long userId,
        String title
) {
}
