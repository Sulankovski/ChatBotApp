package com.chatbotapp.chatbotapp.repositories;

import com.chatbotapp.chatbotapp.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("""
            select chatRoom
            from ChatRoom chatRoom
            """)
    List<ChatRoom> listAllChatRooms();

    @Query("""
            select chatRoom
            from ChatRoom chatRoom
            where chatRoom.userId.id = :userId
            """)
    List<ChatRoom> getAllChatRoomsForUser(Long userId);
}
