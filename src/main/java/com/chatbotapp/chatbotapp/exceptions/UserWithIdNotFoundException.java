package com.chatbotapp.chatbotapp.exceptions;

public class UserWithIdNotFoundException extends RuntimeException {
    public UserWithIdNotFoundException(String userId) {
        super(userId);
    }
}
