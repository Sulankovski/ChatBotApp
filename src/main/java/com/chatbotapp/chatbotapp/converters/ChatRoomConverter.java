package com.chatbotapp.chatbotapp.converters;

import com.chatbotapp.chatbotapp.dto.chat_room.ChatRoomDTO;
import com.chatbotapp.chatbotapp.models.ChatRoom;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomConverter {
    private final UserConverter userConverter;

    public ChatRoomDTO toChatRoomDTO(ChatRoom chatRoom){
        return new ChatRoomDTO(
                chatRoom.getId(),
                chatRoom.getDateCreated(),
                chatRoom.getDateUpdated(),
                chatRoom.getTitle(),
                userConverter.toUserDTO(chatRoom.getUserId())
        );
    }
}
