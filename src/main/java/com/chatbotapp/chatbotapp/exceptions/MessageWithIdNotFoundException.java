package com.chatbotapp.chatbotapp.exceptions;

public class MessageWithIdNotFoundException extends RuntimeException {
    public MessageWithIdNotFoundException(String userId) {
        super(userId);
    }
}
