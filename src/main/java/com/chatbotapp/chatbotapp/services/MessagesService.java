package com.chatbotapp.chatbotapp.services;

import com.chatbotapp.chatbotapp.converters.UserConverter;
import com.chatbotapp.chatbotapp.dto.message.MessageCreationDTO;
import com.chatbotapp.chatbotapp.exceptions.MessageWithIdNotFoundException;
import com.chatbotapp.chatbotapp.exceptions.UserWithIdNotFoundException;
import com.chatbotapp.chatbotapp.models.ChatRoom;
import com.chatbotapp.chatbotapp.models.Message;
import com.chatbotapp.chatbotapp.models.User;
import com.chatbotapp.chatbotapp.repositories.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesService {
    private final MessagesRepository messagesRepository;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    public Message findById(Long messageId) {
        return messagesRepository.findById(messageId).orElseThrow(() -> new MessageWithIdNotFoundException(messageId.toString()));
    }

    public Message createMessage(MessageCreationDTO messageCreationDTO){
        Message message = new Message();
        message.setDateCreated(OffsetDateTime.now());
        message.setSender(userService.findById(messageCreationDTO.sender()));
        message.setReceiver(userService.findById(messageCreationDTO.receiver()));
        message.setChatRoom(chatRoomService.findById(messageCreationDTO.chatRoom()));
        message.setContent(messageCreationDTO.content());

        chatRoomService.updateChatRoom(messageCreationDTO.chatRoom());

        return messagesRepository.save(message);
    }

    public List<Message> getAllMessagesForUser(Long userId) {
        return messagesRepository.getAllMessagesForUser(userId);
    }

    public List<Message> getAllMessagesSendByUser(Long userId) {
        return messagesRepository.getAllMessagesSendByUser(userId);
    }

    public List<Message> getAllMessagesReceivedByUser(Long userId) {
        return messagesRepository.getAllMessagesReceivedByUser(userId);
    }
}
