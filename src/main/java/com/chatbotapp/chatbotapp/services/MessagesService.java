package com.chatbotapp.chatbotapp.services;

import com.chatbotapp.chatbotapp.common.fastAPI.FastApiProperties;
import com.chatbotapp.chatbotapp.dto.message.MessageCreationDTO;
import com.chatbotapp.chatbotapp.exceptions.MessageWithIdNotFoundException;
import com.chatbotapp.chatbotapp.models.Message;
import com.chatbotapp.chatbotapp.repositories.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesService {
    private final MessagesRepository messagesRepository;
    private final UserService userService;
    private final ChatRoomService chatRoomService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FastApiProperties fastApiProperties;

    public Message findById(Long messageId) {
        return messagesRepository.findById(messageId).orElseThrow(() -> new MessageWithIdNotFoundException(messageId.toString()));
    }

    public Message createMessage(MessageCreationDTO messageCreationDTO) {
        Message message = new Message();
        message.setDateCreated(OffsetDateTime.now());
        message.setSender(userService.findById(messageCreationDTO.sender()));
        message.setReceiver(userService.findById(messageCreationDTO.receiver()));
        message.setChatRoom(chatRoomService.findById(messageCreationDTO.chatRoom()));
        message.setContent(messageCreationDTO.content());

        chatRoomService.updateChatRoom(messageCreationDTO.chatRoom());

        return messagesRepository.save(message);
    }

    public List<Message> getAllMessagesForRoom(Long chatRoomId) {
        return messagesRepository.getAllMessagesForRoom(chatRoomId);
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

    public Message createResponseForMessage(MessageCreationDTO messageCreationDTO) {
//        String content = messageCreationDTO.content();
//        String url = fastApiProperties.getUrl();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>(content, headers);
//
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//        String responseMessage = responseEntity.getBody();
        String responseMessage = "This message is response from the bot";

        Message message = new Message();
        message.setDateCreated(OffsetDateTime.now());
        message.setSender(userService.findById(messageCreationDTO.receiver()));
        message.setReceiver(userService.findById(messageCreationDTO.sender()));
        message.setChatRoom(chatRoomService.findById(messageCreationDTO.chatRoom()));
        message.setContent(responseMessage);

        return messagesRepository.save(message);
    }
}
