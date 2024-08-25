package com.chatbotapp.chatbotapp.converters;

import com.chatbotapp.chatbotapp.dto.message.MessageDTO;
import com.chatbotapp.chatbotapp.models.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageConverter {
    private final UserConverter userConverter;
    private final ChatRoomConverter chatRoomConverter;

    public MessageDTO toMessageDTO(Message message) {
        return new MessageDTO(
                message.getId(),
                message.getDateCreated(),
                message.getDateUpdated(),
                userConverter.toUserDTO(message.getSender()),
                userConverter.toUserDTO(message.getReceiver()),
                chatRoomConverter.toChatRoomDTO(message.getChatRoom()),
                message.getContent()
        );
    }
}
