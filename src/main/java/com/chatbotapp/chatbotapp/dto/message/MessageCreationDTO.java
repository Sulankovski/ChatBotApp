package com.chatbotapp.chatbotapp.dto.message;

import com.chatbotapp.chatbotapp.dto.chat_room.ChatRoomDTO;
import com.chatbotapp.chatbotapp.dto.user.UserDTO;
import lombok.NonNull;

public record MessageCreationDTO(
        @NonNull Long sender,
        @NonNull Long receiver,
        @NonNull Long chatRoom,
        String content
) {
}
