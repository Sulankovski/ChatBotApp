package com.chatbotapp.chatbotapp.dto.message;

import com.chatbotapp.chatbotapp.dto.chat_room.ChatRoomDTO;
import com.chatbotapp.chatbotapp.dto.user.UserDTO;

import java.time.OffsetDateTime;

public record MessageDTO(
        Long id,
        OffsetDateTime dateCreated,
        OffsetDateTime dateModified,
        UserDTO sender,
        UserDTO receiver,
        ChatRoomDTO chatRoom,
        String content
) {
}
