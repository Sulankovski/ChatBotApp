package com.chatbotapp.chatbotapp.services;

import com.chatbotapp.chatbotapp.dto.chat_room.ChatRoomCreationDTO;
import com.chatbotapp.chatbotapp.exceptions.ChatRoomWithIdNotFoundException;
import com.chatbotapp.chatbotapp.exceptions.UserWithIdNotFoundException;
import com.chatbotapp.chatbotapp.models.ChatRoom;
import com.chatbotapp.chatbotapp.models.User;
import com.chatbotapp.chatbotapp.repositories.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserService userService;

    public ChatRoom findById(Long chatRoomId){
        return chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new ChatRoomWithIdNotFoundException(chatRoomId.toString()));
    }

    public List<ChatRoom> listAllChatRooms() {
        return chatRoomRepository.listAllChatRooms();
    }

    public List<ChatRoom> getAllChatRoomsForUser(Long userId) {
        return chatRoomRepository.getAllChatRoomsForUser(userId);
    }

    public ChatRoom createChatRoom(ChatRoomCreationDTO chatRoomCreationDTO) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setDateCreated(OffsetDateTime.now());
        chatRoom.setTitle(chatRoomCreationDTO.title());
        chatRoom.setUserId(userService.findById(chatRoomCreationDTO.userId()));
        return chatRoomRepository.save(chatRoom);
    }

    public void updateChatRoom(Long chatRoomId){
        ChatRoom chatRoom = findById(chatRoomId);
        chatRoom.setDateUpdated(OffsetDateTime.now());

        chatRoomRepository.save(chatRoom);
    }
}
