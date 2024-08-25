package com.chatbotapp.chatbotapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ChatBotAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatBotAppApplication.class, args);
    }

}
