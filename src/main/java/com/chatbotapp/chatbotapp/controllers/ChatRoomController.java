package com.chatbotapp.chatbotapp.controllers;

import com.chatbotapp.chatbotapp.converters.ChatRoomConverter;
import com.chatbotapp.chatbotapp.dto.chat_room.ChatRoomCreationDTO;
import com.chatbotapp.chatbotapp.dto.chat_room.ChatRoomDTO;
import com.chatbotapp.chatbotapp.dto.user.UserDTO;
import com.chatbotapp.chatbotapp.models.ChatRoom;
import com.chatbotapp.chatbotapp.services.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/rest/chat-room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatRoomConverter chatRoomConverter;

    @GetMapping("/{chatRoomId}")
    public ChatRoomDTO findById(@PathVariable Long chatRoomId){
        return chatRoomConverter.toChatRoomDTO(chatRoomService.findById(chatRoomId));
    }

    @GetMapping("/all")
    public List<ChatRoomDTO> getAllChatRooms(){
        return chatRoomService.listAllChatRooms().stream().map(chatRoomConverter::toChatRoomDTO).collect(Collectors.toList());
    }

    @GetMapping("/all-for-user/{userId}")
    public List<ChatRoomDTO> getAllChatRoomsForUser(@PathVariable Long userId){
        return chatRoomService.getAllChatRoomsForUser(userId).stream().map(chatRoomConverter::toChatRoomDTO).collect(Collectors.toList());
    }

    @PostMapping("/create-chat-room")
    public ChatRoomDTO createChatRoom(@RequestBody ChatRoomCreationDTO chatRoomCreationDTO){
        return chatRoomConverter.toChatRoomDTO(chatRoomService.createChatRoom(chatRoomCreationDTO));
    }
}
