package com.chatbotapp.chatbotapp.exceptions;

public class ChatRoomWithIdNotFoundException extends RuntimeException{
    public ChatRoomWithIdNotFoundException(String userId) {
        super(userId);
    }
}
