package com.chatbotapp.chatbotapp.dto.chat_room;

import com.chatbotapp.chatbotapp.dto.user.UserDTO;

import java.time.OffsetDateTime;

public record ChatRoomDTO(
        Long id,
        OffsetDateTime dateCreated,
        OffsetDateTime dateModified,
        String title,
        UserDTO user
) {
}
