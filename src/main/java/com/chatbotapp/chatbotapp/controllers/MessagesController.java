package com.chatbotapp.chatbotapp.controllers;

import com.chatbotapp.chatbotapp.converters.MessageConverter;
import com.chatbotapp.chatbotapp.dto.message.MessageCreationDTO;
import com.chatbotapp.chatbotapp.dto.message.MessageDTO;
import com.chatbotapp.chatbotapp.services.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/rest/messages")
public class MessagesController {
    private final MessagesService messagesService;
    private final MessageConverter messageConverter;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/{messageId}")
    public MessageDTO findById(@PathVariable Long messageId) {
        return messageConverter.toMessageDTO(messagesService.findById(messageId));
    }

    @PostMapping("/create-message")
    public MessageDTO createMessage(@RequestBody MessageCreationDTO messageCreationDTO) {
        return messageConverter.toMessageDTO(messagesService.createMessage(messageCreationDTO));
    }

    @PostMapping("/get-response-for-message")
    public MessageDTO getResponseForMessage(@RequestBody MessageCreationDTO messageCreationDTO){
        return messageConverter.toMessageDTO(messagesService.createResponseForMessage(messageCreationDTO));
    }

    @GetMapping("/all-for-room/{roomId}")
    public List<MessageDTO> getAllMessagesForRoom(@PathVariable Long roomId) {
        List<MessageDTO> messageDTOS = messagesService.getAllMessagesForRoom(roomId).stream().map(messageConverter::toMessageDTO).toList();
        messagingTemplate.convertAndSend("/topic/room/" + roomId, messageDTOS);

        return messageDTOS;
    }

    @GetMapping("/all-for-user/{userId}")
    public List<MessageDTO> getAllMessagesForUser(@PathVariable Long userId) {
        return messagesService.getAllMessagesForUser(userId).stream().map(messageConverter::toMessageDTO).collect(Collectors.toList());
    }

    @GetMapping("/all-send-by-user/{userId}")
    public List<MessageDTO> getAllMessagesSendByUser(@PathVariable Long userId) {
        return messagesService.getAllMessagesSendByUser(userId).stream().map(messageConverter::toMessageDTO).collect(Collectors.toList());
    }

    @GetMapping("/all-received-by-user/{userId}")
    public List<MessageDTO> getAllMessagesReceivedByUser(@PathVariable Long userId) {
        return messagesService.getAllMessagesReceivedByUser(userId).stream().map(messageConverter::toMessageDTO).collect(Collectors.toList());
    }
}
