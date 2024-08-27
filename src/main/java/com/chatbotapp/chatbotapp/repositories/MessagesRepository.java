package com.chatbotapp.chatbotapp.repositories;

import com.chatbotapp.chatbotapp.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Message, Long> {
    @Query("""
            select message
            from Message message
            where message.sender.id = :userId or message.receiver.id = :userId
            """)
    List<Message> getAllMessagesForUser(Long userId);

    @Query("""
            select message
            from Message message
            where message.chatRoom.id = :chatRoomId
            """)
    List<Message> getAllMessagesForRoom(Long chatRoomId);

    @Query("""
            select message
            from Message message
            where message.sender.id = :userId
            """)
    List<Message> getAllMessagesSendByUser(Long userId);

    @Query("""
            select message
            from Message message
            where message.receiver.id = :userId
            """)
    List<Message> getAllMessagesReceivedByUser(Long userId);
}
